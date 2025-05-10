/**
 * 
 */

function openPanel() {
    document.getElementById("viewPanel").classList.add("open");
}

function closePanel() {
    document.getElementById("viewPanel").classList.remove("open");
}

const openBtn = document.getElementById('open');
const modal_container = document.getElementById('modal-container');
const closeBtn = document.getElementById('close');

openBtn.addEventListener('click', () => {
    modal_container.classList.add('show');
});

closeBtn.addEventListener('click', () => {
    modal_container.classList.remove('show');
});
