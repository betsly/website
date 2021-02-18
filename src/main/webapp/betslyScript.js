function validate() {
    let psw1 = document.getElementById("inputPassword").value;
    let psw2 = document.getElementById("inputPWconfirmation").value;
    let username = document.getElementById("inputUsername").value;
    let email = document.getElementById("inputEmail").value;

      if (!username) {
        return false;
    } else if (!email) {
        return false;
    } else if (!psw1) {
        return false;
    } else if (!psw2) {
        return false;
    } else if (psw1 != psw2) {
        return false;
    }

    return true;
};
