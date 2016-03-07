use Cwd;

$cwd = getcwd();
$dosFileName = @ARGV[0];
$dosFileName =~ s/$cwd//g;
$dosFileName =~ s,^/,,g;
($unixFileName = $dosFileName) =~ s/\\/\//g;

sub readFile
{
	($dosFileName) = @_;
	my $input = undef;
	if (open(IN, "< $dosFileName"))
	{
		local $lineSeparator = $/;
		undef $/;
		binmode(IN);
		$input = <IN>;
		close(IN);
		$/ = $lineSeparator;
	}
	return $input;
}

sub writeFile
{
	($dosFileName, $content) = @_;
	if (open(OUT, "> $dosFileName"))
	{
		binmode(OUT);
		print OUT "$content";
		close(OUT);
		return 1;
	}
	else
	{
		return 0;
	}
}

sub removeInstrumentation
{
	($in) = @_;
	$out = $in;
	$out =~ s/ {try{System.out.println[^\n]*Exception xexexe\){} (return[^\n]*;)}/$1/gs;
	$out =~ s/try{System.out.println[^\n]*Exception xexexe\){}//gs;
	return $out;
}

if ($dosFileName =~ m/.*\.java$/) 
{
	if ($input = readFile($dosFileName))
	{
		$output = removeInstrumentation($input);
		if ($input ne $output)
		{
			if (writeFile($dosFileName, $output))
			{
				print STDERR "Removed instrumentation from $dosFileName " . length($in) . " => " . length($out) . "\n";
			}
		}
		else
		{
			# print STDERR "No change $dosFileName " . length($in) . " " . length($out) . "\n";
		}
	}
	else
	{
		printf STDERR "undo-java.pl: ERR> failed to open $dosFileName\n";
	}
}
else
{
#	printf STDERR "undo-java.pl: Not a java file: $dosFileName\n";
}
