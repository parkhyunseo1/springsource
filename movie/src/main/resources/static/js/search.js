document.querySelector("[name='keyword']").addEventListener("keyup", (e) => {
  if (e.keyCode == 13) {
    // 검색어 입력 확인
    const keyword = e.target.value;
    if (!keyword) {
      // 없으면 메세지 띄우고 return
      alert("영화명을 입력하세요.");
      return;
    }
    // 있으면 keyword 가져온 후
    // searchForm 찾아서 keyword 입력값을 변경
    const searchForm = document.querySelector("#searchForm");
    searchForm.querySelector("[name='keyword']").value = keyword;
    // form submit
    searchForm.submit();
  }
});
