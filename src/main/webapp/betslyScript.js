function validateRegistration() {
    let psw1 = document.getElementById("Passwort").value;
    let psw2 = document.getElementById("Passwort_besttigen").value;
    let username = document.getElementById("Username").value;
    let email = document.getElementById("Email_Adresse").value;

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

function validateLogin() {
    let email = document.getElementById(("Username")).value;
    let password = document.getElementById("Passwort").value;
    
    if(!email) {
        alert("Please fill out all the fields!");
        return false;
    }
    
    else if(!password) {
        alert("Please fill out all the fields!");
        return false;
    }
    
    return true;
};

function validateJoin() {
    let groupName = document.getElementById(("joinGroupName")).value;
    let groupPassword = document.getElementById("joinGroupPW").value;
    
    if(!groupName) {
        alert("Please fill out all the fields!");
        return false;
    }
    
    else if(!groupPassword) {
        alert("Please fill out all the fields!");
        return false;
    }
    
    return true;
};

function validateCreateGroup() {
    let createGroupName = document.getElementById(("createGroupName")).value;
    let createGroupPassword = document.getElementById("createGroupPassword").value;
    let createGroupDes = document.getElementById("createGroupDes").value;
    
    if(!createGroupName) {
        alert("Please fill out all the fields!");
        return false;
    }
    
    else if(!createGroupPassword) {
        alert("Please fill out all the fields!");
        return false;
    }
    
    else if(!createGroupDes) {
        alert("Please fill out all the fields");
        return false;
    }
    
    return true;
};