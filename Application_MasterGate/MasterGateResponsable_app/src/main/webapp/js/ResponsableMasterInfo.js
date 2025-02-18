document.addEventListener('DOMContentLoaded', function() {
    // Fonction pour gérer le clic sur les liens de la barre latérale
    function handleSidebarClick(event) {
        event.preventDefault();
        
        // Supprimer la classe active de l'élément de la barre latérale actuellement actif
        var activeLink = document.querySelector('.sidebar ul li a.active');
        if (activeLink) {
            activeLink.classList.remove('active');
        }
        
        // Supprimer la classe active de la section actuellement affichée
        var activeSection = document.querySelector('.content section.active');
        if (activeSection) {
            activeSection.classList.remove('active');
        }
        
        // Ajouter la classe active à l'élément cliqué dans la barre latérale
        var clickedLink = event.target;
        clickedLink.classList.add('active');
        
        // Récupérer l'ID de la section correspondante
        var sectionId = clickedLink.getAttribute('href').substring(1);
        
        // Ajouter la classe active à la section correspondante
        var targetSection = document.getElementById(sectionId);
        if (targetSection) {
            targetSection.classList.add('active');
        }
    }

    // Ajouter un écouteur d'événement à tous les liens de la barre latérale
    var sidebarLinks = document.querySelectorAll('.sidebar ul li a');
    sidebarLinks.forEach(function(link) {
        link.addEventListener('click', handleSidebarClick);
    });

    // Gestion du bouton de suppression et du prompt de confirmation
    const deleteButton = document.getElementById('deleteButton');
    const confirmationPrompt = document.getElementById('confirmationPrompt');
    const overlay = document.getElementById('overlay');
    const confirmDelete = document.getElementById('confirmDelete');
    const cancelDelete = document.getElementById('cancelDelete');

    deleteButton.addEventListener('click', function () {
        confirmationPrompt.classList.remove('hidden');
        overlay.classList.remove('hidden');
        document.body.style.pointerEvents = 'none';  // Désactive les clics sur le body
    });

    confirmDelete.addEventListener('click', function () {
        confirmationPrompt.classList.add('hidden');
        overlay.classList.add('hidden');
        document.body.style.pointerEvents = 'auto';  // Réactive les clics sur le body
    });

    cancelDelete.addEventListener('click', function () {
        confirmationPrompt.classList.add('hidden');
        overlay.classList.add('hidden');
        document.body.style.pointerEvents = 'auto';  // Réactive les clics sur le body
    });

    overlay.addEventListener('click', function (event) {
        event.stopPropagation();  // Empêche la propagation des clics sur l'overlay
    });
});
