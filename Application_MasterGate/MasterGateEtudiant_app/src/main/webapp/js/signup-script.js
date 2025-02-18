const formParts = document.querySelectorAll(".form-up");
const btnNext = document.getElementById("next");
const btnPrev = document.getElementById("prev");
const btnSubmit = document.getElementById("submit");
const errorDiv = document.getElementById("error-front");
let i = 0;

btnNext.addEventListener("click", (e)=>{
    e.preventDefault();
    handleNextBtn();
    scrollTo(0, 0);

})
btnPrev.addEventListener("click", (e)=>{
    e.preventDefault();
    handlePrevBtn();
    scrollTo(0, 0);
})



function handleNextBtn(){
    // if all inputs are valid and not empty
    if(checkInputs(i + 1)){ 
        removeErrorMsg();
        // show the prev btn if we are in the first formPart
        if(i === 0) btnPrev.classList.remove("hidden");
        // if we acheived the last formPart then show the submit btn and remove the next btn
        if(i === formParts.length - 2){
            btnNext.classList.add("hidden");
            btnSubmit.classList.remove("hidden");
        } 
        // make sure we don't depasse the last formPart
        if(i < formParts.length - 1){
            formParts[i].classList.add("hidden");
            i++;
            formParts[i].classList.remove("hidden");
        }
    }
    else  showErrorMsg("Veuillez saisir les champs correctement");
}

function handlePrevBtn(){
    removeErrorMsg()
    // remove the PrevBtn if we are in the first form part
    if(i === 1) btnPrev.classList.add("hidden");
    // if we leave the last formPart then remove the submit btn and show the nextBtn
    if(i === formParts.length -1){
        btnNext.classList.remove("hidden");
        btnSubmit.classList.add("hidden");
    } 
    // we are in the range
    if(i > 0){
        formParts[i].classList.add("hidden");
        i--;
        formParts[i].classList.remove("hidden");
    }
}

function checkInputs(i){
     const inputs = document.querySelector(`.form-part-${i}`).querySelectorAll("input");
     for(inpt of inputs){
         if( !inpt.validity.valid || inpt.value.trim().length === 0 ) return false;
     }
    return true;
}

function showErrorMsg(msg){
    errorDiv.classList.remove("hidden");
    errorDiv.textContent = msg;
}
function removeErrorMsg(){
    errorDiv.classList.add("hidden");
    errorDiv.textContent = "";
}

/*
const docTypes = ["application/pdf"];
const imageTypes = ["image/jpeg","image/webp","image/png"]
// handle the upload file things
document.getElementById("docs").addEventListener("change",(e)=>{
    const selectedFile = e.target.files[0];
    const fileName = selectedFile.name;
    const fileSize = selectedFile.size;
    document.getElementById("docs-text").textContent ="";
    document.getElementById("docs-name").textContent = fileName;
    document.getElementById("docs-size").textContent = SizeConversion(fileSize);
    if( !docTypes.includes(selectedFile.type) ){
        showErrorMsg("Le document doit être un pdf");
    }  
    else {
        if ( imageTypes.includes(document.getElementById("photo").files[0].type) ){
            removeErrorMsg(); 
            document.getElementById("submit").disabled = false;
        }
     } 
})
*/

const imageTypes = ["image/jpeg","image/webp","image/png"]
document.getElementById("photo").addEventListener("change",(e)=>{
    const selectedFile = e.target.files[0];
    const fileName = selectedFile.name;
    const fileSize = selectedFile.size;
    document.getElementById("photo-text").textContent ="";
    document.getElementById("photo-name").textContent = fileName;
    document.getElementById("photo-size").textContent = SizeConversion(fileSize);
    if( !imageTypes.includes(selectedFile.type) ){
        showErrorMsg("L'image doit être soit: jpg, png ou webp");
    }  
    else {
//       if ( docTypes.includes(document.getElementById("docs").files[0].type) ){
//        removeErrorMsg(); 
//        document.getElementById("submit").disabled = false;
//       } 
        document.getElementById("submit").disabled = false;
        removeErrorMsg(); 

    } 
})

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

