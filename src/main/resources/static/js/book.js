// createBookFrm.addEventListener("submit",event=>{
//    const frm = document.querySelector("#createBookFrm");
//    event.preventDefault();
//
//    fetch(`/api/books`,{
//        method:'POST',
//        headers: {
//            'Content-Type': 'application/json'
//        },
//        body: JSON.stringify(
//            {
//                name : frm.name,
//                author:frm.author
//            }
//        )
//    }).then(response => {
//        if (response.ok)
//            return response.json();
//    }).then(data => {
//        alert('succeed');
//    })
// });