// get references to form elements
const form = document.getElementById("user-form");
const userIdInput = document.getElementById("userId");
const nicknameInput = document.getElementById("nickname");
const passwordInput = document.getElementById("password");
const errorMessage = document.getElementById("error-msg");

// handle form submission
form.addEventListener("submit", function(event) {
    event.preventDefault();
    // reset error message
    errorMessage.textContent = "";
    const userId = userIdInput.value;
    const nickname = nicknameInput.value;
    const password = passwordInput.value;
    // check if Id is valid
    if (!/^[a-zA-Z0-9]+$/.test(userId)) {
        errorMessage.textContent = "ID는 영문 혹은 숫자여야합니다.";
        alert(errorMessage.textContent);
        return;
    }
    // check if password is long enough
    else if (password.length < 8 || password.length > 16) {
        errorMessage.textContent = "패스워드는 8자 이상, 16자 이하여야합니다.";
        alert(errorMessage.textContent);
        return;
    }
    else if(!/^(?!.*[^a-zA-Z0-9_]{2,10})/.test(nickname)){
        errorMessage.textContent = "닉네임은 2글자 이상, 10글자 이하이며 특수문자를 포함할 수 없습니다.";
        alert(errorMessage.textContent);
        return;
    }
    alert("회원가입 성공");
    // form is valid, submit it
    form.submit();
});
