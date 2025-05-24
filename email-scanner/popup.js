document.addEventListener('DOMContentLoaded', () => {
    const scanButton = document.getElementById('scanButton');
    const statusBox = document.getElementById('statusBox');

    scanButton.addEventListener('click', async () => {
        // Disable the button while scanning
        scanButton.disabled = true;
        statusBox.className = 'status scanning';
        statusBox.textContent = 'Scanning email content...';

        try {
            // Get the active tab
            const [tab] = await chrome.tabs.query({ active: true, currentWindow: true });
            
            // Request email content from the content script
            chrome.tabs.sendMessage(tab.id, { action: 'getEmailContent' }, (response) => {
                if (chrome.runtime.lastError) {
                    statusBox.className = 'status warning';
                    statusBox.textContent = 'Please open an email to scan.';
                    scanButton.disabled = false;
                    return;
                }

                if (!response || !response.content) {
                    statusBox.className = 'status warning';
                    statusBox.textContent = 'No email content found. Please make sure an email is open.';
                } else {
                    // For now, just show that we successfully got the content
                    statusBox.className = 'status safe';
                    statusBox.textContent = 'Email content extracted successfully!';
                }
                
                scanButton.disabled = false;
            });
        } catch (error) {
            statusBox.className = 'status warning';
            statusBox.textContent = 'Error scanning email. Please try again.';
            scanButton.disabled = false;
        }
    });
});