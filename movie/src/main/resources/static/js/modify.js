// 포스터 추가 시 이벤트
document.querySelector(".uploadResult ul").addEventListener("click", (e) => {
  // a 태그 기능 중지
  if (e.target.tagName !== "I") return;
  e.preventDefault();

  // href 값 가져오기
  const element = e.target.closest("li");

  if (confirm("정말로 이미지를 삭제하시겠습니까?")) {
    element.remove();
  }
});

// modifyForm 찾은 후 action = "/movie/remove"
const form = document.querySelector("#actionForm");
const removeBtn = document.querySelector("#createForm .bg-red-600");
const listBtn = document.querySelector("#createForm .bg-orange-100");

if (removeBtn) {
  removeBtn.addEventListener("click", () => {
    if (!confirm("정말로 삭제하시겠습니까?")) {
      return;
    }

    form.action = "/movie/remove";
    form.submit();
  });
}
listBtn.addEventListener("click", () => {
  form.querySelector("[name='mno']").remove();

  form.method = "get";
  form.action = "/movie/list";
  form.submit();
});
