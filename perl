#!/usr/bin/perl
use strict;
use warnings;
use HTML::TreeBuilder;
use MIME::Lite;

# Path to the TestNG HTML report file
my $html_report_file = 'testng-report.html';

# Parse the HTML report
my $tree = HTML::TreeBuilder->new;
$tree->parse_file($html_report_file);

# Extract passed and failed test case details
my $passed_tests = 0;
my $failed_tests = 0;

# Find elements containing passed and failed test case counts
my @test_results = $tree->look_down(
    '_tag' => 'div',
    'class' => 'total'
);

foreach my $result (@test_results) {
    my $text = $result->as_text;
    if ($text =~ /Passed: (\d+)/) {
        $passed_tests = $1;
    } elsif ($text =~ /Failed: (\d+)/) {
        $failed_tests = $1;
    }
}

# Print the test case details
print "Passed tests: $passed_tests\n";
print "Failed tests: $failed_tests\n";

# Send email notification with test case details
my $email_subject = 'TestNG Results';
my $email_body = "Passed tests: $passed_tests\nFailed tests: $failed_tests";

# Configure the email
my $email = MIME::Lite->new(
    'From'     => 'your_email@example.com',
    'To'       => 'recipient@example.com',
    'Subject'  => $email_subject,
    'Data'     => $email_body,
);

# Send the email
$email->send;

# Clean up
$tree->delete;

exit;
