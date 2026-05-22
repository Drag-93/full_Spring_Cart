/**
 * 관리자 결제 관리 전용 JavaScript
 */

// 1. 모달 닫기 기능
function closeFn() {
    const modal = document.querySelector('.modal');
    if (modal) {
        modal.style.display = 'none';
    }
}

// 2. 결제 상세 조회 및 모달 창 띄우기 (Fetch API 비동기 통신)
function paymentDetalFn(event, paymentId) {
    if (event) event.preventDefault(); // 기본 이벤트 방지

    if (!paymentId) {
        alert("올바르지 않은 결제 ID입니다.");
        return;
    }

    // Spring Boot 백엔드 REST API 엔드포인트 호출 (프로젝트 설계에 맞게 URL 확인 필요)
    fetch(`/admin/payment/detail/${paymentId}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("결제 정보를 가져오는 데 실패했습니다.");
            }
            return response.json();
        })
        .then(data => {
            // 모달 데이터 바인딩
            document.querySelector('.modal .id').textContent = data.id || '';
            document.querySelector('.modal .memberId').textContent = data.memberId || '';
            document.querySelector('.modal .paymentType').textContent = data.paymentType || '';
            document.querySelector('.modal .orderPost').textContent = data.orderPost || '';
            document.querySelector('.modal .orderAddr').textContent = data.orderAddr || '';

            // 가격 데이터 뒤에 '원' 부착 (이전 유저 데이터 특성 반영)
            const price = data.payResult || '0';
            document.querySelector('.modal .payResult').textContent = price.toString().contains?.('원') ? price : price + '원';

            document.querySelector('.modal .orderMethod').textContent = data.orderMethod || '';

            // 날짜 포맷팅 (YYYY-MM-DD HH:mm:ss)
            if (data.createTime) {
                const date = new Date(data.createTime);
                const formattedDate = date.getFullYear() + '-' +
                    String(date.getMonth() + 1).padStart(2, '0') + '-' +
                    String(date.getDate()).padStart(2, '0') + ' ' +
                    String(date.getHours()).padStart(2, '0') + ':' +
                    String(date.getMinutes()).padStart(2, '0') + ':' +
                    String(date.getSeconds()).padStart(2, '0');
                document.querySelector('.modal .createTime').textContent = formattedDate;
            } else {
                document.querySelector('.modal .createTime').textContent = '-';
            }

            // 하위 결제 상품 리스트 출력 가공
            const itemContainer = document.querySelector('.modal .paymentItemEntities');
            itemContainer.innerHTML = ''; // 기존 데이터 초기화

            if (data.paymentItemEntities && data.paymentItemEntities.length > 0) {
                const ul = document.createElement('ul');
                ul.style.listStyle = 'none';
                ul.style.padding = '0';
                ul.style.margin = '5px 0 0 0';

                data.paymentItemEntities.forEach(item => {
                    const li = document.createElement('li');
                    li.style.fontSize = '13px';
                    li.style.color = '#555';
                    li.style.marginBottom = '4px';
                    li.textContent = `▪ ${item.paymentItemTitle} (사이즈: ${item.paymentItemSize}) - ${item.paymentItemPrice}`;
                    ul.appendChild(li);
                });
                itemContainer.appendChild(ul);
            } else {
                itemContainer.textContent = '주문 상품 없음';
            }

            // 모달 활성화 (CSS로 .modal { display:none; } 상태여야 함)
            const modal = document.querySelector('.modal');
            if (modal) {
                modal.style.display = 'block';
            }
        })
        .catch(error => {
            console.error("Error:", error);
            alert(error.message);
        });
}

// 3. 검색 조건 및 키워드 유효성 검사 후 검색 요청
function searchFn(event) {
    if (event) event.preventDefault();

    const searchInput = document.getElementById('search');
    const searchForm = document.querySelector('.search form');

    if (!searchInput || searchInput.value.trim() === '') {
        alert('검색어를 입력해 주세요.');
        if (searchInput) searchInput.focus();
        return;
    }

    // 폼 서브밋 실행 -> /admin/payment/search?subject=...&search=...
    if (searchForm) {
        searchForm.submit();
    }
}

// 모달 바깥 영역 클릭 시 닫히는 서브 이벤트 (사용자 편의성)
window.addEventListener('click', function(event) {
    const modal = document.querySelector('.modal');
    if (event.target === modal) {
        modal.style.display = 'none';
    }
});