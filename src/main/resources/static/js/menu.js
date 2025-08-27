function toggleMenu() {
    const dropdown = document.getElementById("dropdown");
    dropdown.classList.toggle("show");
  }

  window.addEventListener("click", function(e) {
    const dropdown = document.getElementById("dropdown");
    const icon = document.querySelector(".user-icon");

    if (!dropdown.contains(e.target) && !icon.contains(e.target)) {
      dropdown.classList.remove("show");
    }
  });