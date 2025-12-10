document.addEventListener("DOMContentLoaded", () => {

  const track = document.querySelector("#carrusel .carousel-track");
  const prevBtn = document.querySelector("#carrusel .carousel-btn.prev");
  const nextBtn = document.querySelector("#carrusel .carousel-btn.next");

  let index = 0;

  function updateCarousel() {
    const width = track.children[0].clientWidth;
    track.style.transform = `translateX(-${index * width}px)`;
  }

  nextBtn.addEventListener("click", () => {
    index++;
    if (index >= track.children.length) index = 0;
    updateCarousel();
  });

  prevBtn.addEventListener("click", () => {
    index--;
    if (index < 0) index = track.children.length - 1;
    updateCarousel();
  });

  // Si el usuario cambia el tamaño de la pantalla → recalcular
  window.addEventListener("resize", updateCarousel);



  /* =========================
     ACORDEONES ✅
  ========================= */
  document.querySelectorAll(".accordion-header").forEach(header => {

    header.addEventListener("click", () => {
      const item = header.parentElement;
      const isOpen = item.classList.contains("active");

      // cerrar todos
      document.querySelectorAll(".accordion-item").forEach(el => {
        el.classList.remove("active");
      });

      // abrir actual
      if (!isOpen) {
        item.classList.add("active");
      }
    });

  });


});


