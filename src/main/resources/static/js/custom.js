
//Flip//
      $(function(){
        prettyPrint();
        
        $(".card-grid").flip({
          trigger: 'hover'
        });

        $("#card-1").flip();

        $("#card-2").flip({
          axis: 'x',
          trigger: 'hover'
        });

        $("#card-3").flip({
          axis: 'x',
          trigger: 'hover',
          reverse: true
        });

        $("#card-4").flip({
          trigger: 'manual'
        });

        $("#flip-btn").click(function(){
          $("#card-4").flip(true);
        });

        $("#unflip-btn").click(function(){
          $("#card-4").flip(false);
        });

        $("#toggle-btn").click(function(){
          $("#card-4").flip('toggle');
        });
    
        $("#card-5").flip({
          axis: 'y',
          trigger: 'click'
        });
        $("#axis-x-btn").click(function(){
          $("#card-5").flip({axis: 'x'});
        });
        $("#axis-y-btn").click(function(){
          $("#card-5").flip({axis: 'y'});
        });
        $("#reverse-true-btn").click(function(){
          $("#card-5").flip({reverse: true});
        });
        $("#reverse-false-btn").click(function(){
          $("#card-5").flip({reverse: false});
        });
        $("#card-6").flip({
          trigger: 'click'
        });
        $('#assign-done').click(function(){
          $("#card-6").on('flip:done',function(){
            $(this).fadeOut(400,function(){
              $(this).fadeIn();
            });
          });
        });
        $("#card-7").flip({
          trigger: 'click'
        });
        $('#assign-change').click(function(){
          $("#card-7").on('flip:change',function(){
            $(this).flip('toggle');
          });
        });
        var not = function(axis){
            if (axis == 'x'){
             return 'y';
            }else{
                return 'x';
            }
        }
        var c7_nextAxis = 'y';
        $('#toggleaxis').click(function(){
          c7_nextAxis = not(c7_nextAxis);
          $('.card').flip({axis: c7_nextAxis});
        });
        $("#card-fluid").flip({
          axis: 'y',
          trigger: 'click',
      forceWidth: false,
      forceHeight: false
        }).find('.front, .back').css({
      'width': '100%',
      'height': '100%'
    });
      });
    //  / /    ///

   // const countEl = document.getElementById('cart-count');
    //const addBtns = document.querySelectorAll('.add-btn');
    //const removeBtns = document.querySelectorAll('.ms-btn');

   // let count = 0;

   // addBtns.forEach(btn => {
     // btn.addEventListener('click', () => {
      //  count++;
      //  updateCart();
      //});
    //});

   // removeBtns.forEach(btn => {
    //  btn.addEventListener('click', () => {
     //   if (count > 0) count--;
      //  updateCart();
     // });
    //});

   // function updateCart() {
    //  countEl.textContent = count;
   // }
   // Example product list

const products = [
  { id: 1, name: "Falkia", price: 650 },
  { id: 2, name: "Aguja Morada", price: 3500 },
  { id: 3, name: "Lavanda", price: 600 },
];

const productsContainer = document.getElementById("products");
const cartContainer = document.getElementById("cart");

// Load cart from localStorage or initialize empty
let cart = JSON.parse(localStorage.getItem("cart")) || [];

// Save the cart to localStorage
function saveCart() {
  localStorage.setItem("cart", JSON.stringify(cart));
}

// Render product list
function renderProducts() {
  productsContainer.innerHTML = products
    .map(
      (p) => `
      <div class="product">
        <strong>${p.name}</strong> - $${p.price}
        <button onclick="addToCart(${p.id})">Add to Cart</button>
      </div>
    `
    )
    .join("");
}

// Add product to cart
function addToCart(id) {
  const product = products.find((p) => p.id === id);
  const existingItem = cart.find((item) => item.id === id);

  if (existingItem) {
    existingItem.qty++;
  } else {
    cart.push({ ...product, qty: 1 });
  }

  saveCart();
  renderCart();
}

// Remove a product completely from cart
function removeFromCart(id) {
  cart = cart.filter((item) => item.id !== id);
  saveCart();
  renderCart();
}

// Decrease quantity by 1
function decreaseQty(id) {
  const item = cart.find((i) => i.id === id);
  if (!item) return;
  item.qty--;
  if (item.qty <= 0) {
    removeFromCart(id);
  } else {
    saveCart();
    renderCart();
  }
}

// Calculate total
function getTotal() {
  return cart.reduce((sum, item) => sum + item.price * item.qty, 0);
}

// Render cart section
function renderCart() {
  if (cart.length === 0) {
    cartContainer.innerHTML = "<p>Your cart is empty.</p>";
    return;
  }

  cartContainer.innerHTML = cart
    .map(
      (item) => `
      <div class="cart-item">
        ${item.name} - $${item.price} × ${item.qty} 
        = <strong>$${item.price * item.qty}</strong>
        <button onclick="decreaseQty(${item.id})">−</button>
        <button onclick="addToCart(${item.id})">+</button>
        <button onclick="removeFromCart(${item.id})">Remove</button>
      </div>
    `
    )
    .join("") +
    `<p><strong>Total: $${getTotal()}</strong></p>`;
}

// Initialize on page load
renderProducts();
renderCart();

    