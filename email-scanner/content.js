// Function to extract email content from Gmail
function getGmailContent() {
    const emailBody = document.querySelector('.a3s.aiL');
    return emailBody ? emailBody.innerText : null;
}

// Function to extract email content from Outlook
function getOutlookContent() {
    const emailBody = document.querySelector('[role="main"] .allowTextSelection');
    return emailBody ? emailBody.innerText : null;
}

// Listen for messages from the popup
chrome.runtime.onMessage.addListener((request, sender, sendResponse) => {
    if (request.action === 'getEmailContent') {
        let emailContent = null;
        
        // Check if we're on Gmail
        if (window.location.hostname === 'mail.google.com') {
            emailContent = getGmailContent();
        }
        // Check if we're on Outlook
        else if (window.location.hostname.includes('outlook')) {
            emailContent = getOutlookContent();
        }

        sendResponse({ content: emailContent });
    }
});