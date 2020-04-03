<a href="https://www.twilio.com">
  <img src="https://static0.twilio.com/marketing/bundles/marketing/img/logos/wordmark-red.svg" alt="Twilio" width="250" />
</a>

# Twilio Account Security Quickstart - Twilio Authy and Twilio Verify

> We are currently in the process of updating this sample template. If you are encountering any issues with the sample, please open an issue at [github.com/twilio-labs/code-exchange/issues](https://github.com/twilio-labs/code-exchange/issues) and we'll try to help you.

![](https://github.com/TwilioDevEd/account-security-quickstart-spring/workflows/Java-Gradle/badge.svg)

A simple Java, Spring and AngularJS implementation of a website that uses Twilio Authy to protect all assets within a folder with two-factor authentication. Additionally, it shows a Verify Phone Verification implementation.

It uses four channels for delivery, SMS, Voice, Soft Tokens, and Push Authentication. You should have the [Authy App](https://authy.com/download/) installed to try Soft Token and Push Authentication support.

Learn more about Account Security and when to use the Authy API vs the Verify API in the [Account Security documentation](https://www.twilio.com/docs/verify/authy-vs-verify).


#### Two-Factor Authentication Demo
- URL path "/protected" is protected with both user session and Twilio Authy Two-Factor Authentication
- One Time Passwords (SMS and Voice)
- SoftTokens
- Push Notifications (via polling)

#### Phone Verification
- Phone Verification
- SMS or Voice Call

### How to get an Authy API Key
You will need to create a new Authy application in the [console](https://www.twilio.com/console/authy/). After you give it a name you can view the generated Account Security production API key. This is the string you will later need to set up in your environmental variables.

![Get Authy API Key](api_key.png)

### Setup
- Clone this repo
- Run `gradle build`
- Register for a [Twilio Account](https://www.twilio.com/).
- Setup an Account Security app via the [Twilio Console](https://twilio.com/console).
- Grab an Application API key from the Dashboard and paste it in `.env.example`
- Save the `.env.example` file as `.env`
- Load the configuration with `source .env`
- Run `gradle appRun` from the cloned repo to run the app


## Meta

* No warranty expressed or implied. Software is as is. Diggity.
* The CodeExchange repository can be found [here](https://github.com/twilio-labs/code-exchange/).
* [MIT License](http://www.opensource.org/licenses/mit-license.html)
* Lovingly crafted by Twilio Developer Education.
