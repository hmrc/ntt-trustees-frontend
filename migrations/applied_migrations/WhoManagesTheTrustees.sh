#!/bin/bash

echo ""
echo "Applying migration WhoManagesTheTrustees"

echo "Adding routes to conf/app.routes"
echo "" >> ../conf/app.routes
echo "GET        /whoManagesTheTrustees                       controllers.WhoManagesTheTrusteesController.onPageLoad()" >> ../conf/app.routes

echo "Adding messages to conf.messages"
echo "" >> ../conf/messages.en
echo "whoManagesTheTrustees.title = whoManagesTheTrustees" >> ../conf/messages.en
echo "whoManagesTheTrustees.heading = whoManagesTheTrustees" >> ../conf/messages.en

echo "Migration WhoManagesTheTrustees completed"
