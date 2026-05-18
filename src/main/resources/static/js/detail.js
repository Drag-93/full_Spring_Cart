const id=document.querySelector('#id')
const itemTitle=document.querySelector('#itemTitle')
const itemDetail=document.querySelector('#itemDetail')
const itemPrice=document.querySelector('#itemPrice')
const memberId=document.querySelector('#memberId')
const itemSize=document.querySelector('#itemSize')
const total=document.querySelector('span#total')

const totalFn=()=>{
    const totalVal=itemSize.value*itemPrice.value
    console.log(totalVal)
    total.innerText=totalVal;
};

itemSize.addEventListener('change',totalFn);

const cartFn=(event)=>{
    event.preventDefault();
    const url=`/cart/insert/addCart2`;
    const itemData={
        id:id.value,
        itemTitle:itemTitle.value,
        itemDetail:itemDetail.value,
        itemPrice:itemPrice.value,
        memberId:memberId.value,
        itemSize:itemSize.value,
    }
    fetch( url , {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(itemData),
    })
        .then(res=> res.json())
        .then(rs=> {
            console.log(rs)
            // 정상 -> /cart/cartList1
            if(rs==1){
                location.href=`/cart/cartList1/${memberId.value}`;
            }
            // 예러 -> 그대로
        })
        .catch(err=> console.log(err));
    alert("장바구니GO");
}

(()=>totalFn())();