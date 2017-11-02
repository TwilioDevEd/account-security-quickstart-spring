![Twilio Logo](./twilio_logo_red.png)
# Twilio Account Security Quickstart - Two-Factor Authentication and Phone Verification

A simple NodeJS and AngularJS implementation of a website that uses Twilio Account Security services to protect all assets within a folder. Additionally, it shows a Phone Verification implementation.

It uses four channels for delivery, SMS, Voice, Soft Tokens, and Push Notifications. You should have the [Authy App](https://authy.com/download/) installed to try Soft Token and Push Notification support.

This app uses [MongoDB](https://www.mongodb.com/) as a data store. You will have to install MongoDB as well and make sure it is running.

#### Two-Factor Authentication Demo
- URL path "/protected" is protected with both user session and Twilio Two-Factor Authentication
- One Time Passwords (SMS and Voice)
- SoftTokens
- Push Notifications (via polling)

#### Phone Verification
- Phone Verification
- SMS or Voice Call

### Setup
- Clone this repo
- Run `npm install`
- Register for a [Twilio Account](https://www.twilio.com/).
- Setup an Account Security app via the [Twilio Console](https://twilio.com/console).
- Grab an Application API key from the Dashboard and paste it in `.env.example`
- Save the `.env.example` file as `.env`
- Check and make sure MongoDB is up and running
    - On *NIX, may be as easy as running `mongod` in a new tab.
- Run `nodemon .` or `node .` from the cloned repo to run the app

### License
- MIT
