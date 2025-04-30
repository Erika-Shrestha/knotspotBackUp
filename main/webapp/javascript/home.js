/**
 * 
 */

class Card{
	constructor(cardElement){
		this.cardElement = cardElement
		this.imageWrapper = cardElement.querySelector('.image-wrapper')
		const cardHeight = this.cardElement.getBoundingClientRect().height
		const imageWrapperHeight = this.imageWrapper.getBoundingClientRect().height
		this.heightDiff = cardHeight - imageWrapperHeight
		console.log('card created')
	}
	
	update(){
		const topOffsetCard = this.cardElement.getBoundingClientRect().top
		const progress = topOffsetCard / window.innerHeight
		let yPosition = progress * this.heightDiff
		yPosition = Math.min(0, Math.max(yPosition, this.heightDiff));
		this.imageWrapper.style.transform = `translate(0, ${yPosition}px)`
		console.log('update')
			}
		}
		
function initCards(){
	const cardElements = document.querySelectorAll('.card')
	const cards = Array.from(cardElements).map((cardElement) => new Card(cardElement))
	
	function update(){
		cards.forEach(card => card.update())
	}
	
	function onScroll(){
		requestAnimationFrame(() =>{
			update()
		})
	}
	
	requestAnimationFrame(update)
		window.addEventListener('scroll', onScroll)
	}

initCards();
			

function initScrollVideo() {
    const video = document.getElementById('scrollVideo');
    const section = document.querySelector('.video-scroll-section');

	video.addEventListener('loadedmetadata', () => {
		const duration = video.duration;
		const sectionHeight = section.offsetHeight - window.innerHeight;

		function onScroll() {
			const scrollPosition = window.scrollY - section.offsetTop;
			const scrollFraction = Math.min(Math.max(scrollPosition / sectionHeight, 0), 1);
			video.currentTime = scrollFraction * duration;
		}

		window.addEventListener('scroll', onScroll);
		onScroll();
	});
}

initScrollVideo();

function navScroll() {
  const body = document.body;
  let lastScroll = 0;
  
  window.addEventListener('scroll', () => {
    const currentScroll = window.pageYOffset;

    if (currentScroll > lastScroll && !body.classList.contains("scroll-down")) {
      body.classList.remove("scroll-up");
      body.classList.add("scroll-down");
    }

    if (currentScroll < lastScroll && !body.classList.contains("scroll-up")) {
      body.classList.remove("scroll-down");
      body.classList.add("scroll-up");
    }


    if (currentScroll <= 0) {
      body.classList.remove("scroll-up");
    }

    lastScroll = currentScroll;
  });
}

navScroll();
