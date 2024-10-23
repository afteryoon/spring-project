document.addEventListener('DOMContentLoaded', () => {

    // const token = document.querySelector('input[name="_csrf"]').value;
    // const header = document.querySelector('input[name="_csrf"]').name;
    // const header = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

    // axios.interceptors.request.use(function (config) {
    //     config.headers[header] = token;
    //     return config;
    // });
    const state = {
        page: 0,
        size: 10,
        loading: false,
        hasMore: true
    };

    const loadComments = () => {
        if (state.loading || !state.hasMore) return;
        state.loading = true;
        const articleId = document.querySelector("#article-id").value

            fetch(`/api/comments/${articleId}?page=${state.page}&size=${state.size}`, {
                method: `GET`,
                headers: {
                    'Content-Type': 'application/json'
                },
            }).then(res => res.json())
                .then(data => {
                    if (data.content) {
                        data.content.forEach(addCommentToList);
                        state.hasMore = !data.last;  // 서버에서 보내주는 hasNext 값
                        state.page += 1;
                    }
                }).catch(e =>{
                    console.error("load comments error",e);
            }).finally(() => {
                state.loading = false;
            });

        updateLoadMoreButton();
    };

    const  addCommentToList = (comment)=>{
        const commentElement = document.createElement('div');
        commentElement.className = 'card mb-2';
        commentElement.innerHTML = `
            <div class="card-body">
                <h5 class="card-title">Comment #${comment.id}</h5>
                <h6 class="card-subtitle mb-2 text-muted">${new Date(comment.createdAt).toLocaleString()}</h6>
                <p class="card-text">${comment.body}</p>
            </div>
        `;
        document.querySelector("#comments-list").prepend(commentElement);
    }

    const updateLoadMoreButton = () => {
        const loadMoreBtn = document.querySelector("#load-more-btn");
        if (loadMoreBtn) {
            loadMoreBtn.style.display = state.hasMore ? 'block' : 'none';
        }
    };

    loadComments();

    document.querySelector("#load-more-btn")?.addEventListener("click", loadComments);

    const commentFrm = document.querySelector("#comment-form");
    commentFrm.addEventListener("submit",(e)=>{
        e.preventDefault();
        const createComment = {
            body : commentFrm.querySelector('textarea').value,
            articleId : document.querySelector("#article-id").value
        }

        fetch(`/api/comments`, {
            method : 'POST',
            headers : {
                'Content-Type' : 'application/json',
                //[header]: token
            },
            body: JSON.stringify(createComment)
        })
            .then(response => response.json())
            .then(comment => {
                addCommentToList(comment);
                document.getElementById('comment-content').value = '';
            })
            .catch(error => {
                console.error('addCommentError:', error);
            });

    });


});

