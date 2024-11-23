$(document).ready(function() {
    $('#summernote').summernote({
        height: 800,
        lang: 'ko-KR',
        toolbar: [
            ['fontsize', ['fontsize']],
            ['style', ['bold', 'italic', 'underline', 'clear']],
            ['color', ['color']],
            ['table', ['table']],
            ['para', ['ul', 'ol', 'paragraph']],
            ['insert', ['hr']]
        ],
        fontNames: ['Arial', 'Comic Sans MS', '맑은 고딕', '궁서', '굴림체'],
        callbacks: {
            onImageUpload: function(files) {
                for (let file of files) {
                    uploadImage(file);
                }
            }
        }
    });
});
var customizingInfoIdx = document.getElementById('customizingInfoIdx').value;

var canvas = new fabric.Canvas('canvas', {
    width: 500,
    height: 500,
    isDrawingMode: false
});


var circleOutline = new fabric.Circle({
    left: 0,
    top: 0,
    radius: 250,
    stroke: 'black',
    strokeWidth: 2,
    fill: 'transparent',
    hasControls: false,
    hasBorders: false
});
canvas.add(circleOutline);

var currentPenColor = '#000000';
var currentBrushSize = 2;
var currentTool = 'pen';


document.getElementById('dragModeBtn').addEventListener('click', function() {
    canvas.isDrawingMode = false;
    currentTool = 'drag';
});

document.getElementById('penBtn').addEventListener('click', function() {
    canvas.isDrawingMode = true;
    currentTool = 'pen';
    canvas.freeDrawingBrush.color = currentPenColor;
    canvas.freeDrawingBrush.width = currentBrushSize;
});
var currentBrushType = 'simple';  // 기본 브러시 타입

document.getElementById('brushTypeSelect').addEventListener('change', function(e) {
    currentBrushType = e.target.value;
    setBrushType(currentBrushType);
});

function setBrushType(brushType) {
    switch (brushType) {
        case 'bubble':
            canvas.freeDrawingBrush = new fabric.CircleBrush(canvas);
            break;
        case 'pen':
            canvas.freeDrawingBrush = new fabric.PencilBrush(canvas);
            break;
        case 'spray':
            canvas.freeDrawingBrush = new fabric.SprayBrush(canvas);
            break;
        default:
            canvas.freeDrawingBrush = new fabric.PencilBrush(canvas);
    }

    canvas.freeDrawingBrush.color = currentPenColor;
    canvas.freeDrawingBrush.width = currentBrushSize;
}

document.getElementById('colorPicker').addEventListener('input', function(e) {
    currentPenColor = e.target.value;
    if (canvas.isDrawingMode) {
        canvas.freeDrawingBrush.color = currentPenColor;
    }
});

document.getElementById('brushSize').addEventListener('input', function(e) {
    currentBrushSize = e.target.value;
    if (canvas.isDrawingMode) {
        canvas.freeDrawingBrush.width = currentBrushSize;
    }
});

// 이미지 업로드
document.getElementById('fileInput').addEventListener('change', function(e) {
    var reader = new FileReader();
    reader.onload = function(event) {
        var imgObj = new Image();
        imgObj.src = event.target.result;
        imgObj.onload = function() {
            var image = new fabric.Image(imgObj);
            image.set({
                left: 0,
                top: 0,
                hasControls: true,
                hasBorders: true,
                lockMovementX: false,
                lockMovementY: false
            });
            canvas.add(image);
        };
    };
    reader.readAsDataURL(e.target.files[0]);
});

// 저장 버튼 클릭 시 이미지 캡처 및 서버로 전송

document.getElementById('saveBtn').addEventListener('click', function() {

    // 원형 캔버스 크기
    var canvasWidth = 500;
    var canvasHeight = 500;
    var radius = canvasWidth / 2;

    // 새로운 캔버스를 만들어 원형 영역만 추출
    var tempCanvas = document.createElement('canvas');
    tempCanvas.width = canvasWidth;
    tempCanvas.height = canvasHeight;
    var ctx = tempCanvas.getContext('2d');

    ctx.beginPath();
    ctx.arc(canvasWidth / 2, canvasHeight / 2, radius, 0, Math.PI * 2, false);
    ctx.clip();

    // 캔버스의 내용을 새로운 캔버스에 그리기
    var dataURL = canvas.toDataURL();
    var img = new Image();
    img.src = dataURL;
    img.onload = function() {
        // 원형 마스크로 캔버스를 자르기
        ctx.drawImage(img, 0, 0, canvasWidth, canvasHeight);
        tempCanvas.toBlob(function (blob) {
            const formData = new FormData();
            formData.append('file', blob, 'customized-image.png');

            // 서버로 Blob 데이터 전송
            fetch('/saveImage', {
                method: 'POST',
                body: formData,
            })
                .then((response) => response.json()) // 서버 응답 처리
                .then((data) => {
                    var savedImageURL = data.savedImageURL;

                    document.getElementById('customizingImgField').value = savedImageURL;

                    // 모달 닫기
                    document.getElementById('myModal').style.display = 'none';
                })
                .catch((error) => {
                    console.error('이미지 저장 오류:', error); // 오류 처리
                });
        }, 'image/png'); // Blob 형식 및 이미지 타입 지정
    };

});

// 모달 열기/닫기
document.getElementById('openModalBtn').addEventListener('click', function() {
    document.getElementById('myModal').style.display = 'block';
});

document.getElementById('closeModalBtn').addEventListener('click', function() {
    document.getElementById('myModal').style.display = 'none';
});

// 모달 외부 클릭 시 모달 닫기
window.onclick = function(event) {
    if (event.target === document.getElementById('myModal')) {
        document.getElementById('myModal').style.display = 'none';
    }
};