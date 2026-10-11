const complaintForm = document.getElementById("complaintForm");

complaintForm.addEventListener("submit", async function (event) {
    event.preventDefault();

    const complaint = {
        name: document.getElementById("name").value.trim(),
        email: document.getElementById("email").value.trim(),
        subject: document.getElementById("subject").value.trim(),
        description: document.getElementById("description").value.trim()
    };

    try {
        const response = await fetch(
            "http://localhost:8080/api/complaints",
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(complaint)
            }
        );

        if (response.ok) {
            const result = await response.json();

            alert("Complaint submitted successfully! ID: " + result.id);

            complaintForm.reset();
        } else {
            const message = await response.text();
            alert("Submission failed. Please check the backend. " + message);
        }
    } catch (error) {
        alert("Cannot connect to the backend. Make sure Spring Boot is running.");
        console.error(error);
    }
});
