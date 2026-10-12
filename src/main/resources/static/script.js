
const apiUrl = "/api/modules";

const form = document.getElementById("moduleForm");
const moduleTable = document.getElementById("moduleTable");
const formMessage = document.getElementById("formMessage");

function escapeHtml(value) {
    return String(value).replace(/[&<>"']/g, character => ({
        "&": "&amp;",
        "<": "&lt;",
        ">": "&gt;",
        '"': "&quot;",
        "'": "&#39;"
    })[character]);
}

function showMessage(message, isError = false) {
    formMessage.textContent = message;
    formMessage.style.color = isError ? "#b4232b" : "#137447";
}

async function loadModules() {
    try {
        const response = await fetch(apiUrl);

        if (!response.ok) {
            throw new Error("Could not load modules.");
        }

        const modules = await response.json();

        document.getElementById("totalCount").textContent = modules.length;
        document.getElementById("highCount").textContent =
            modules.filter(module => module.riskLevel === "HIGH").length;
        document.getElementById("mediumCount").textContent =
            modules.filter(module => module.riskLevel === "MEDIUM").length;
        document.getElementById("lowCount").textContent =
            modules.filter(module => module.riskLevel === "LOW").length;

        if (modules.length === 0) {
            moduleTable.innerHTML =
                '<tr><td colspan="4">No modules yet. Add your first module.</td></tr>';
            return;
        }

        moduleTable.innerHTML = modules.map(module => {
            const level = String(module.riskLevel).toLowerCase();

            return `
                <tr>
                    <td>${escapeHtml(module.moduleName)}</td>
                    <td><strong>${Number(module.riskScore).toFixed(1)}</strong></td>
                    <td>
                        <span class="risk-badge risk-${level}">
                            ${escapeHtml(module.riskLevel)}
                        </span>
                    </td>
                    <td>
                        <button class="delete-btn"
                            onclick="deleteModule(${Number(module.id)})">
                            Delete
                        </button>
                    </td>
                </tr>
            `;
        }).join("");
    } catch (error) {
        moduleTable.innerHTML =
            '<tr><td colspan="4">Unable to load modules. Check that the server is running.</td></tr>';
    }
}

form.addEventListener("submit", async event => {
    event.preventDefault();

    const moduleName = document.getElementById("moduleName").value.trim();
    const previousDefects =
        Number(document.getElementById("previousDefects").value);
    const testFailures =
        Number(document.getElementById("testFailures").value);
    const complexity = Number(document.getElementById("complexity").value);
    const businessImpact =
        Number(document.getElementById("businessImpact").value);

    if (
        !moduleName ||
        !Number.isInteger(previousDefects) ||
        previousDefects < 0 || previousDefects > 10 ||
        !Number.isInteger(testFailures) ||
        testFailures < 0 || testFailures > 10 ||
        !Number.isInteger(complexity) ||
        complexity < 1 || complexity > 5 ||
        !Number.isInteger(businessImpact) ||
        businessImpact < 1 || businessImpact > 5
    ) {
        showMessage("Enter valid values within the displayed ranges.", true);
        return;
    }

    const payload = {
        moduleName,
        previousDefects,
        testFailures,
        complexity,
        businessImpact
    };

    try {
        const response = await fetch(apiUrl, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(payload)
        });

        if (!response.ok) {
            const errorText = await response.text();
            throw new Error(errorText || "Unable to add module.");
        }

        const result = await response.json();

        showMessage(
            `Added successfully. Risk score: ${result.riskScore} (${result.riskLevel}).`
        );

        form.reset();
        document.getElementById("previousDefects").value = 0;
        document.getElementById("testFailures").value = 0;
        document.getElementById("complexity").value = 3;
        document.getElementById("businessImpact").value = 3;

        await loadModules();
    } catch (error) {
        showMessage(error.message || "Something went wrong.", true);
    }
});

async function deleteModule(id) {
    if (!confirm("Are you sure you want to delete this module?")) {
        return;
    }

    try {
        const response = await fetch(`${apiUrl}/${id}`, {
            method: "DELETE"
        });

        if (!response.ok) {
            throw new Error("Unable to delete module.");
        }

        showMessage("Module deleted successfully.");
        await loadModules();
    } catch (error) {
        showMessage(error.message || "Something went wrong.", true);
    }
}

loadModules();
