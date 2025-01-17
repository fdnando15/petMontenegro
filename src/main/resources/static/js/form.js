async function authform(URL,method = "POST", body){
    const response = await fetch(URL, {
        method: method,
        headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': 'Bearer '+ localStorage.getItem("token")
        },
        body: body
    }
);
    if(!response.ok){
        throw new Error("Exeption Ocurred: "+ response.text)
    }

    const data = await response.json();
    return data;
}

async function fields(fieldslist){
    const result = {};
    for (const field of fieldslist) {
        const element = document.getElementById(field);
        if (element) {
            result[field] = element.value;
        }
    }
    return JSON.stringify(result);
}
