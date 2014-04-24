<?php
// This file is an example of how to use PHP iCalWriter.

require_once("iCalWriter.php");

$iCal = new iCalWriter;

$iEvent = new iCalEvent;

$iAlarm = new iCalDisplayAlarm;

$iRecurrence = new iCalRecurrence;

$iCal->setHTMLOutput();
//$iCal->setFileOutput();
$iCal->setFileName("/var/www/iCal/test.ics");

$iCal->start();


$iEvent->setStart("2008", "02", "10", 0, 1, "", 1, "14", "12");
$iEvent->setEnd("2008", "02", "12", 1, 0, "", 1, "12");
$iEvent->setShortDescription("Ein Test");
$iEvent->setLongDescription("Eine lange lange und noch längere Beschreibung");
$iEvent->setLocation("Oranienburg\, Deutschland");
$iEvent->setCategories("Testkat, Alle Kategorien");
$iEvent->setStatus("cancelled");
$iEvent->setURL("http://www.sebastiankleine.de");
$iEvent->addAttendee("Kleine\, Sebastian");
$iEvent->setRequestStatusSuccessful("OK", 4);
$iEvent->addResource("Beamer, Room");
$iEvent->addxProp("myProp", "no useful thing");

// add alarm
$iAlarm->setAlarmBeforeStart(0, 2, 0);
$iAlarm->setAlarmRepeat(4, 0, 0, 15);
$iAlarm->setText("display this message");
$iEvent->addAlarm($iAlarm);

// add recurrence
// every 5 days 10 times
// but not after 15 days
$iRecurrence->setRecurrenceFrequenceDaily();
$iRecurrence->setRecurrenceCount(10);
$iRecurrence->setRecurrenceInterval(5);
$iRecurrence->setExceptionFrequenceDaily();
$iRecurrence->setExceptionCount(1);
$iRecurrence->setExceptionInterval(15);
$iEvent->addRecurrence($iRecurrence);

// add another recurrence
// first clear the already used object to reuse it
$iRecurrence->clear();
// add recurrence every last friday every month for ever
$iRecurrence->setRecurrenceFrequenceMonthly();
$iRecurrence->addRecurrenceByDay("FR", -1);
$iEvent->addRecurrence($iRecurrence);


// add the event to the output
$iCal->add($iEvent);

// clear the event to reuse it
$iEvent->clear();


$iEvent->setStart("2008", "01", "22", 0, 1);
$iEvent->setEnd("2008", "01", "24", 0, 1);
//$iEvent->setDuration(0, 0, 3);
$iEvent->setShortDescription("Zweiter Test");
$iEvent->setComment("Eine Anmerkung. Aber diese interessiert ja doch keinen...");
//$iEvent->setLocation("Oranienburg, Deutschland");
//$iEvent->setCategories("Testkat, Alle Kategorien");
//$iEvent->setStatus("cancelled");
//$iEvent->setURL("http://www.sebastiankleine.de");
$iCal->add($iEvent);




$iCal->end();

?>
