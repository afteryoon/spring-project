// DELETE /articles/{id}
const deleteButton =document.getElementById("delete-btn");

if(deleteButton) {
    deleteButton.addEventListener("click", () =>{
        const id = document.getElementById('article-id').value;
        fetch(`/api/articles/${id}` , {
            method:'DELETE'
        }).then(() =>{
            alert('삭제 완료')
            location.replace('/articles');
        });
    });
}
const updateButton =  document.getElementById("modify-btn");
    if(updateButton) {
        updateButton.addEventListener("click", () => {
            const articleData = {
                title: document.getElementById("title").value,
                content: document.getElementById("content").value
            };
            const id = document.getElementById("article-id").value;
            fetch(`/api/articles/${id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(articleData)
            }).then(response => {
                if (response.ok) {
                    alert("수정 완료")
                    location.reload();
                } else {
                    alert("실패")
                }
            });
        });
    }

    const createArticle = () => {
        const articleData = {
            title: document.getElementById("title").value,
            content: document.getElementById("content").value
        };
      fetch(`/api/articles`, {
          method:'POST',
          headers: {
              'Content-Type': 'application/json'
          },
          body: JSON.stringify(articleData)
      }).then(response => {
            if (response.ok) {
                return response.json();  // 응답 데이터를 JSON으로 파싱
            } else {
                throw new Error("Article creation failed");
            }
        }).then(data => {
                // data.id에 접근 가능
                alert('succeed');
                location.replace(`/articles/${data.id}`);  // 생성된 article의 id로 이동
            })
    };

