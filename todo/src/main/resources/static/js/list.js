// 완료를 클릭하면 체크박스 value 가져오기
document.querySelector("tbody").addEventListener("click", (e) => {
  const chk = e.target;

  console.log(chk.value);
  console.log(chk.checked);

  const cf = document.querySelector("#comForm");
  cf.querySelector("[name='id']").value = chk.value;
  cf.querySelector("[name='completed']").value = chk.checked;
  console.log(cf.innerHTML);
  cf.submit();
});

//
