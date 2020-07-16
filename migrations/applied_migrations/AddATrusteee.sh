#!/bin/bash

echo ""
echo "Applying migration AddATrusteee"

echo "Adding routes to conf/app.routes"
echo "" >> ../conf/app.routes
echo "GET        /addATrusteee                       controllers.AddATrusteeeController.onPageLoad()" >> ../conf/app.routes

echo "Adding messages to conf.messages"
echo "" >> ../conf/messages.en
echo "addATrusteee.title = addATrusteee" >> ../conf/messages.en
echo "addATrusteee.heading = addATrusteee" >> ../conf/messages.en

echo "Migration AddATrusteee completed"
