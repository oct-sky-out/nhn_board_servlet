const fileName = document.querySelector(".file-name");
const fileInput = document.querySelector(".file-input");
const fileUploadForm = document.querySelector(".uploader");

fileInput.addEventListener("change", async (e) => {
  e.preventDefault();

  const image = e.target.files[0];
  fileName.innerHTML = image.name;
})
