package org.example;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.Page;

public class SimplePlaywrightTest {
    public static void main(String[] args) {
        // Launch Playwright
        try (Playwright playwright = Playwright.create()) {
            // Launch browser (using Chromium by default, you can change to firefox() or webkit())
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false)  // Set to true if you don't want to see the browser UI
                    .setSlowMo(100));    // Slows down Playwright operations by 100ms

            // Create a new page
            Page page = browser.newPage();

            try {
                // Navigate to website
                System.out.println("Navigating to website...");
                page.navigate("https://www.google.com/");

                // Wait for the page to load
                page.waitForLoadState();

                // Wait for page to load completely
                page.waitForLoadState(LoadState.NETWORKIDLE);

                // Find the search box by its name attribute and type text
                page.fill("textarea[name='q']", "Playwright automation tutorial");

                // Wait for the search button to be visible
                page.waitForSelector("input[name='btnK']");

                // Click the search button
                // Using JavaScript click to avoid any overlay issues
                page.evaluate("document.querySelector('input[name=\"btnK\"]').click()");


                // Wait briefly to see the result
                System.out.println("Button clicked successfully");
                page.waitForTimeout(5000); // Wait for 5 seconds
            } catch (PlaywrightException e) {
                System.err.println("Playwright operation failed: " + e.getMessage());
                e.printStackTrace();
            } finally {
                // Close browser
                browser.close();
                System.out.println("Test completed");
            }
        } catch (Exception e) {
            System.err.println("Failed to initialize Playwright: " + e.getMessage());
            e.printStackTrace();
        }
    }
}