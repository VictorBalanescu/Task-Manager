
function apriModal(button) {
    document.getElementById('modal-id').value = button.getAttribute('data-id');
    document.getElementById('modal-nome').value = button.getAttribute('data-nome');
    document.getElementById('modal-cognome').value = button.getAttribute('data-cognome');
  

    document.getElementById('modalUtente').style.display = 'block';
  }

  function chiudiModal() {
    document.getElementById('modalUtente').style.display = 'none';
  }

  // Chiudi il modal se clicchi fuori dal contenuto
  window.onclick = function(event) {
    const modal = document.getElementById('modalUtente');
    if (event.target === modal) {
      chiudiModal();
    }
  }