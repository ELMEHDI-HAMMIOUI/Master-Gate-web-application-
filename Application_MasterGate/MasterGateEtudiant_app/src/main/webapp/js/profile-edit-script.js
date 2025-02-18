const errorDiv = document.getElementById("error-front");

const imageTypes = ["image/jpeg","image/webp","image/png"]

// handle the upload photo things
document.getElementById("photo").addEventListener("change",(e)=>{
    const selectedFile = e.target.files[0];
    console.log(selectedFile)
    const fileName = selectedFile.name;
    const fileSize = selectedFile.size;
    document.getElementById("photo-text").textContent ="";
    document.getElementById("photo-name").textContent = fileName;
    document.getElementById("photo-size").textContent = SizeConversion(fileSize);
    if( !imageTypes.includes(selectedFile.type) ){
        showErrorMsg("L'image doit être soit: jpg, png ou webp");
    }  
    else {
        removeErrorMsg(); 
    } 
})

function showErrorMsg(msg){
    errorDiv.classList.remove("hidden");
    errorDiv.textContent = msg;
}
function removeErrorMsg(){
    errorDiv.classList.add("hidden");
    errorDiv.textContent = "";
}

// // convert the sizes
function SizeConversion(sizeInBytes) {
    const units = ['bytes', 'KB', 'MB', 'GB', 'TB'];
    let unitIndex = 0;
    let size = sizeInBytes;

    while (size >= 1024 && unitIndex < units.length - 1) {
        size /= 1024;
        unitIndex++;
    }

    return size.toFixed(2) + units[unitIndex];
}

// // show the settings options
// document.getElementById("settings-btn").addEventListener("click",(e)=>{
//     e.preventDefault();
//     document.getElementById("control-btns").classList.toggle("hidden");
// })