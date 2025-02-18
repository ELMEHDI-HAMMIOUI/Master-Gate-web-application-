const specialiteInput = document.getElementById("specialite");
const coordinateurInput = document.getElementById("nom_coordinateur");
const lieuConcoursInput = document.getElementById("lieu_concours");
const informationInput = document.getElementById("information");
const dateError = document.getElementById('dateError');
const debutInscriptionInput = document.getElementById('d_debut_inscription');
const finInscriptionInput = document.getElementById('d_fin_inscription');
const affPreselectionInput = document.getElementById('d_aff_preselection');
const concoursInput = document.getElementById('d_concours');
const affResultatConcoursInput = document.getElementById('d_aff_resultat_concours');
const currentDateInput = document.getElementById('currentDate');
const maxAgeInput = document.getElementById("max_age");
const maxAnneeEtudeInput = document.getElementById("max_annee_etude");
const note_seuilInput = document.getElementById("note_seuil");
const note_min_semestreInput = document.getElementById("note_min_semestre");
const conditionsError = document.getElementById('conditionsError');

const confirmationPrompt = document.querySelector('.confirmation-prompt');
const confirmButton = document.querySelector('.confirm-button');
const cancelButton = document.querySelector('.cancel-button');
const addButton = document.getElementById('addbtn');
const selectBtn = document.querySelector(".select-btn"),
    items = document.querySelectorAll(".item");
let addbtn = document.getElementById("addbtn");

selectBtn.addEventListener("click", () => {
    selectBtn.classList.toggle("open");
});

// 1) Add the nicknames of the selected filieres to the select-btn
items.forEach(item => {
    const checkbox = item.querySelector("input[type='checkbox']");
    item.addEventListener("click", () => {
        checkbox.checked = !checkbox.checked;
        item.classList.toggle("checked");

        let checked = document.querySelectorAll(".checked"),
            btnText = document.querySelector(".btn-text");

        if (checked && checked.length > 0) {
            const selectedSurnoms = Array.from(checked).map(item => item.querySelector(".item-text-surnom").innerText);
            btnText.innerText = selectedSurnoms.join(' . ');
           
        } else {
            btnText.innerText = "Choisissez les filières admises pour l'inscription au master";
        }
        updateBorderColor();
         addbtnStatus();
    });
});

// 2) Select all and deselect all filieres
function toggleSelection() {
    const checkboxes = document.querySelectorAll("input[type='checkbox']:not(#selectAll)");
    const toggleButton = document.getElementById("toggleButton");
    let btnText = document.querySelector(".btn-text");
    let allChecked = true;

    checkboxes.forEach(checkbox => {
        if (!checkbox.checked) {
            allChecked = false;
        }
    });

    checkboxes.forEach(checkbox => {
        if (!allChecked) {
            checkbox.checked = true;
            checkbox.closest('.item').classList.add("checked");
        } else {
            checkbox.checked = false;
            checkbox.closest('.item').classList.remove("checked");
        }
    });
    updateBorderColor();
    addbtnStatus();
    if (!allChecked) {
        toggleButton.innerText = "Désélectionner tous";
        btnText.innerText = "Toutes les filières sont admissibles pour l'inscription";
    } else {
        toggleButton.innerText = "Sélectionner tous";
        btnText.innerText = "Veuillez sélectionner les filières autorisées pour l'inscription au master";
    }
}

// Function to update the border color based on the state of the checkboxes
function updateBorderColor() {
    const checkboxes = document.querySelectorAll("input[name='selectedFiliere']");
    const selectboc = document.querySelector(".container-select");
    let isChecked = false;

    checkboxes.forEach(checkbox => {
        if (checkbox.checked) {
            isChecked = true;
            return; // Exit the loop early since we found a checked checkbox
        }
    });

    if (isChecked) {
        
    } else {
    }

    return isChecked;
}

document.addEventListener('DOMContentLoaded', function() {
    const items = document.querySelectorAll(".item");

    items.forEach(item => {
        item.addEventListener('click', updateBorderColor);
    });

    updateBorderColor();
    addbtnStatus();
});

// Hide the dropdown when clicking outside the select
document.addEventListener("click", (event) => {
    const containerSelect = document.querySelector(".container-select");
    const isClickInside = containerSelect.contains(event.target);

    if (!isClickInside) {
        containerSelect.querySelector(".select-btn").classList.remove("open");
    }
});




document.addEventListener('DOMContentLoaded', function() {
    isEmpty(specialiteInput);
    isEmpty(coordinateurInput);
    isEmpty(lieuConcoursInput);
    isEmpty(informationInput);
    
	addbtnStatus();
    specialiteInput.addEventListener("input", function() {
        isEmpty(specialiteInput);
        addbtnStatus();
    });
    coordinateurInput.addEventListener("input", function() {
        isEmpty(coordinateurInput);
        addbtnStatus();
    });
    lieuConcoursInput.addEventListener("input", function() {
        isEmpty(lieuConcoursInput);
        addbtnStatus();
    });
    informationInput.addEventListener("input", function() {
        isEmpty(informationInput);
        addbtnStatus();
    });
    
    debutInscriptionInput.addEventListener("input", function() {
        addbtnStatus();
    });
    finInscriptionInput.addEventListener("input", function() {
        addbtnStatus();
    });
    affPreselectionInput.addEventListener("input", function() {
        addbtnStatus();
    });
    concoursInput.addEventListener("input", function() {
        addbtnStatus();
    });
    affResultatConcoursInput.addEventListener("input", function() {
        addbtnStatus();
    });
    //Conditions traitment 
	maxAgeValidate();
	maxAnneeValidate();
	noteSeuilValidate();
	noteMinSemestreValidate();
	
     maxAgeInput.addEventListener("input", function() {
        maxAgeValidate();
        addbtnStatus();
    });;
	maxAnneeEtudeInput.addEventListener("input", function() {
        maxAnneeValidate();
        addbtnStatus();
    });
	note_seuilInput.addEventListener("input", function() {
        noteSeuilValidate();
        addbtnStatus();
    });
	note_min_semestreInput.addEventListener("input", function() {
        noteMinSemestreValidate();
        addbtnStatus();
    });
    
    
});


function isEmpty(input) {
    var val = input.value;
    var empty = false;
    if (val.trim() === "" || val === "--") {
        empty = true;
    } else {
        empty = false;
    }
    return empty;
}

function addbtnStatus(){
	var Emptyinforamtion = isEmpty(specialiteInput) ||  isEmpty(coordinateurInput) || isEmpty(lieuConcoursInput) || isEmpty(informationInput)
	var validDates = validateDates(); 
    var conditions = maxAgeValidate() && noteMinSemestreValidate() && noteSeuilValidate() && maxAnneeValidate();
    var selectCecked = updateBorderColor();

    if (!Emptyinforamtion && validDates && conditions && selectCecked) {
		addbtn.disabled = false;
		addbtn.style.backgroundColor  ="#52cb71";
		addbtn.style.pointerEvents = "auto";
	}else{
		addbtn.disabled = true;
		addbtn.style.backgroundColor  ="#9eac9b";  
		addbtn.style.pointerEvents = "none"; 
	}
}


  
   

    
//traitment des dates l'ordre chronologique et empty
function validateDates() {
	
	const currentDate=new Date(currentDateInput.value);
    const debutInscriptionDate = new Date(debutInscriptionInput.value);
    const finInscriptionDate = new Date(finInscriptionInput.value);
    const affPreselectionDate = new Date(affPreselectionInput.value);
    const concoursDate = new Date(concoursInput.value);
    const affResultatConcoursDate = new Date(affResultatConcoursInput.value);

    
    if(debutInscriptionDate < currentDate || isEmpty(debutInscriptionInput)){

		finInscriptionInput.disabled = true;
        affPreselectionInput.disabled = true;
        concoursInput.disabled = true;
        affResultatConcoursInput.disabled = true;
        if(!isEmpty(debutInscriptionInput)){
			   dateError.innerText = " * La date de début d'inscription ne peut pas être antérieure à la date actuelle.";
		}
	}else{
	
			finInscriptionInput.disabled = false;
		 	affPreselectionInput.disabled = false;
	        concoursInput.disabled = false;
	        affResultatConcoursInput.disabled = false;
	        dateError.innerText = " ";
    
	    if (debutInscriptionDate >= finInscriptionDate ||  isEmpty(finInscriptionInput) ) {
	     
	        affPreselectionInput.disabled = true;
	        concoursInput.disabled = true;
	        affResultatConcoursInput.disabled = true;
	       if(!isEmpty(finInscriptionInput)){
			   dateError.innerText = " * La date de debut d'inscription doit etre postérieure au date de fin d'inscription .";
		   }
	        
	    } else {
	 
	        affPreselectionInput.disabled = false;
	        concoursInput.disabled = false;
	        affResultatConcoursInput.disabled = false;
			dateError.innerText = " ";
	        if (finInscriptionDate >= affPreselectionDate ||     isEmpty(affPreselectionInput)) {
	     
	
	            concoursInput.disabled = true;
	            affResultatConcoursInput.disabled = true;
	           	if(!isEmpty(affPreselectionInput)){
				   dateError.innerText = " * La date d'affichage des résultats de la preselection doit etre postérieure au date de fin d'inscription ";
			   }
	        } else {
	            concoursInput.disabled = false;
	            affResultatConcoursInput.disabled = false;
				dateError.innerText = " ";
				
   
	            if (affPreselectionDate >= concoursDate ||isEmpty(concoursInput)) {
	            
	
	                affResultatConcoursInput.disabled = true;
	                if(!isEmpty(concoursInput)){
							dateError.innerText = " * La date concours doit etre postérieure au date d'affichage des résultats de la preselection ";
					}
	            } else {
	            
	                affResultatConcoursInput.disabled = false;
	                dateError.innerText = " ";
	                
	                if (concoursDate >= affResultatConcoursDate ||  isEmpty(affResultatConcoursInput)) {
						if(!isEmpty(affResultatConcoursInput)){
							dateError.innerText = " * La date des résultats Finale doit être postérieure au la date  du concours.";
						}
						return false;
					} else {
					    dateError.innerText = " ";
					    return true
					}

	            }
	        }
	    }
    }
}


function maxAgeValidate() {
    var empty = isEmpty(maxAgeInput);
    var val = maxAgeInput.value;
    if (/^-?\d+$/.test(val) && !empty && val >= 20 ) {
        conditionsError.innerText = "";
        return true;
    } else {
        if (!empty) {
            conditionsError.innerText = " * L'âge doit être supérieur ou égal à 20.";
        }
        return false;
    }
}

function maxAnneeValidate() {
    var empty = isEmpty(maxAnneeEtudeInput);
    var val = maxAnneeEtudeInput.value;
    if (/^-?\d+$/.test(val) && !empty && val > 0) {
        conditionsError.innerText = "";
        return true;
    } else {
        if (!empty) {
            conditionsError.innerText = " * Vérifier le champ années d'études.";
        }
        return false;
    }
}

function noteMinSemestreValidate() {
    var empty = isEmpty(note_min_semestreInput);
    var val = note_min_semestreInput.value;
    if (/^\d{1,2}\.?\d{0,2}$/.test(val) && !empty && val >= 0 && val <= 20) {
        conditionsError.innerText = "";
        return true;
    } else {
        if (!empty) {
            conditionsError.innerText = " * La note doit être comprise entre 0 et 20.";
        }
        return false;
    }
}


//le seuil doit etre supperieure à 10
function noteSeuilValidate(){
	 var empty = isEmpty(note_seuilInput);
    var val = note_seuilInput.value;
    if (/^\d{1,2}\.?\d{0,2}$/.test(val) && !empty && val >= 10 && val <= 20) {
        conditionsError.innerText = "";
        return true;
    } else {
        if (!empty) {
            conditionsError.innerText = " * la note du seuil  doitêtre ecomprise entre 10 et 20.";
        }
        return false;
    }
}








//l'orsque on cancel on lance un prompt pur ne pérdre pas les données
document.addEventListener("DOMContentLoaded", function() {
  const cancelButton = document.querySelector(".btn2");

  cancelButton.addEventListener("click", function(event) {
    event.preventDefault(); 
    const confirmation = confirm("Tous les informations que vous avez entrées seront perdues. voulez vous quitter  ?");
    if (confirmation) {
      window.location.href = "ResponsableHome"; 
    } else {
    }
  });
});


//l'orsque on click sur ajouter on affiche prepmt de confirmation 
addButton.addEventListener('click', function() {
    confirmationPrompt.style.display = 'flex';
});

confirmButton.addEventListener('click', function() {
    document.querySelector('form').submit();
});

cancelButton.addEventListener('click', function() {
    confirmationPrompt.style.display = 'none';
});
function hideCards() {
    var successCard = document.querySelector('.status-card.success');
    var errorCard = document.querySelector('.status-card.error');
    
    if (successCard) {
      successCard.style.display = 'none';
    }
    if (errorCard) {
      errorCard.style.display = 'none';
    }
  }

  document.querySelector('.continue-btn').addEventListener('click', function() {
    hideCards(); 
  });

  document.querySelector('.retry-btn').addEventListener('click', function() {
    hideCards(); 
});



function removeBodyScroll() {
     document.body.style.overflow = 'hidden';
}
function bringBackBodyScroll() {
    wrapper.style.display = 'none';
}


document.addEventListener("DOMContentLoaded", function() {
    function handleSubmit() {
        document.body.style.overflow = 'hidden';
        var wrapper = document.querySelector('.wrapper-container');
        wrapper.style.display = 'flex';
    }

    function hide() {
        document.body.style.overflow = ''; 
        var wrapper = document.querySelector('.wrapper-container');
        wrapper.style.display = 'none';
    }
});



