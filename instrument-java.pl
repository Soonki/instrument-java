use Cwd;

#
# Soonki Ji (soonki.ji@gmail.com)
#
# 2006-09-25 Created instrument-java.pl, instrument-java.bat, instrument-java-all.bat.
# 2012-07-18 Added undo-java.pl, undo-java.bat, undo-java-all.bat.
# 2014-11-26 Fixed instrument-java.pl to handle @PathParam("id") in the column list.
#

$true = 1;
$false = 0;
$LINESEP = $\;
my $trimSpaces = 1;

sub remove_newline
{
	my ($s) = @_;
	$s =~ s/[\n\s]+/ /g;
	return $s;
}

sub trim
{
	my ($s) = @_;
	$s =~ s/\s+/ /g;
	$s =~ s/^\s+//g;
	$s =~ s/\s+$//g;
	return $s;
}

sub removeCommaInGenericType_X
{
	my ($s) = @_;
print STDERR "DBG>      beforeRemovingComma=[$s]\n";
	$s =~ s/,/./g;
	$s =~ s/\s+//g;
print STDERR "DBG>      afterRemovingComma=[$s]\n";
	return $s;
}

sub processColumns
{
	my $maxElems = 5;
	my $result = "";
	my ($s) = @_;
#print STDERR "DBG> arg=[$s]\n";
	for ($i = 0; $i < 4; $i++) {
		$s =~ s/<([^<]*?)>/ /g;					# Remove generic type arg.
	}
	$s =~ s/\/\/.*?\n/\n/g;
	$s =~ s/\/\*.*?\*\///g;
	$s =~ s/\/\/.*?NEWLINEEND//g;
	$s =~ s/NEWLINEBEGIN.*?NEWLINEEND//g;
	$s =~ s/\s+/ /g;
#print STDERR "DBG>   arg=[$s]\n";
	my @typeNameArr = map { s/^\s+//g; $_ } split(/,/, $s);
	for my $typeName (@typeNameArr) {
#print STDERR "DBG>     typeName=[$typeName]\n";

		$typeName = trim($typeName);

		# @PathParam
		($sPathParam) = ($typeName =~ m/PathParamXXX_PathParam_OPENPAREN_XXX"(.*?)"XXX_PathParam_CLOSEPAREN_XXX/);
		if ($sPathParam ne "")
		{
			$sPathParam = $1;
			$typeName =~ s/\@PathParam\S+\s+//g;
			$typeName = trim($typeName);
		}

		# @SecuredParam
		($sSecuredParam) = ($typeName =~ m/SecuredParamXXX_SecuredParam_OPENPAREN_XXX"(.*?)"XXX_SecuredParam_CLOSEPAREN_XXX/);
		if ($sSecuredParam ne "")
		{
			$sSecuredParam = $1;
			$typeName =~ s/\@SecuredParam\S+\s+//g;
			$typeName = trim($typeName);
		}

		# @QueryParam
		($sQueryParam) = ($typeName =~ m/QueryParamXXX_QueryParam_OPENPAREN_XXX"(.*?)"XXX_QueryParam_CLOSEPAREN_XXX/);
		if ($sQueryParam ne "")
		{
			$sQueryParam = $1;
			$typeName =~ s/\@QueryParam\S+\s+//g;
			$typeName = trim($typeName);
		}

		# @SuppressWarnings
		($sSuppressWarnings) = ($typeName =~ m/SuppressWarningsXXX_SuppressWarnings_OPENPAREN_XXX"(.*?)"XXX_SuppressWarnings_CLOSEPAREN_XXX/);
		if ($sSuppressWarnings ne "")
		{
			$sSuppressWarnings = $1;
			$typeName =~ s/\@SuppressWarnings\S+\s+//g;
			$typeName = trim($typeName);
		}

		# @FormParam
		($sFormParam) = ($typeName =~ m/FormParamXXX_FormParam_OPENPAREN_XXX"(.*?)"XXX_FormParam_CLOSEPAREN_XXX/);
		if ($sFormParam ne "")
		{
			$sFormParam = $1;
			$typeName =~ s/\@FormParam\S+\s+//g;
			$typeName = trim($typeName);
		}

		my ($type, $name) = ($typeName =~ m/(.*) (\w+)/);
		$type = trim($type);
		my $tmp = "";

		# @PathParam
		if ($sPathParam ne "") {
			$tmp .= "\@PathParam(\'$sPathParam\') ";
		}

		# @SecuredParam
		if ($sSecuredParam ne "") {
			$tmp .= "\@SecuredParam(\'$sSecuredParam\') ";
		}

		# @QueryParam
		if ($sQueryParam ne "") {
			$tmp .= "\@QueryParam(\'$sQueryParam\') ";
		}

		# @SuppressWarnings
		if ($sSuppressWarnings ne "") {
			$tmp .= "\@SuppressWarnings(\'$sSuppressWarnings\') ";
		}

		# @FormParam
		if ($sFormParam ne "") {
			$tmp .= "\@FormParam(\'$sFormParam\') ";
		}

		$tmp .= $type . " "	. $name . "=";
		$tmp .= "\"";
		$tmp .= "+";
		if ($type =~ /\]$/) {
			$tmp .= "($name != null ? $name + \" length=\" + $name.length : null)";
			# tmp .= "($name != null ? \"'\" + java.util.Arrays.toString(java.util.Arrays.copyOfRange($name, 0, $name.length > $maxElems ? $maxElems : $name.length)) + \"'\" + ($name.length > $maxElems ? \"...\" : \"\") + \" len=\" + $name.length : null)";
		} else {
			$tmp .= "\"'\" + $name + \"'\"";
		}		
		$tmp .= "+";
		$tmp .= "\"";

		if ($result eq "") {
			$result .= $tmp;
		} else {
			$result .= ", " . $tmp;
		}
	}
	return $result;
}

sub processColumns_XX
{
	my ($methodName, $cols) = @_;
# print STDERR "DBG> processColumns: methodName=$methodName, cols=$cols, _=@_\n";
	if ($methodName ne "if" and $methodName ne "while" and $methodName ne "for")
	{
		$cols =~ s/\t/ /g;						# Remove tabs
		$cols =~ s/ +/ /g;						# Squeeze spaces
		$cols =~ s/\/\/[^\n]*//g;				# Remove C++ style comment
		$cols =~ s/\/\*.*?\*\///g;				# Remove C style comment
		$cols =~ s/NEWLINEBEGIN.*?NEWLINEEND/ /g;
		$cols =~ s/\s+/ /g;
		$cols =~ s/^\s+$//g;
		my @arrArgs = split(",", $cols);
		my $out = "";
# print STDERR "DBG> processColumns: input $cols\n";
		foreach $sTypeVarname (@arrArgs) {
			$sTypeVarname = trim($sTypeVarname);
			my ($sType, $sVarname) = ($sTypeVarname =~ /^(.*)\s+(\w+)/);
			$sType = trim($sType);
			$sVarname = trim($sVarname);
# print STDERR "DBG>    element >> type: $sType, name: $sVarname\n";
			if ($out ne "")
			{
				$out .= ", ";
			}

			if ($sType =~ /\[/ || $sType =~ /\.\.\./)
			{
				$out .= "$sType $sVarname=\" + java.util.Arrays.toString($sVarname) + \"";
			}
			else
			{
				$out .= "$sType $sVarname='\" + $sVarname + \"'";
			}
		}
# print STDERR "DBG>    output >> $out\n";
		return "$out";
	}
	else
	{
		return "keyword:$methodName";
	}
}

sub readFile
{
	my ($dosFileName) = @_;
	my $in = undef;
	if (open(IN, "< $dosFileName"))
	{
		my $lineSeparator = $/;
		undef $/;
		binmode(IN);
		$in = <IN>;
		close(IN);
		$/ = $lineSeparator;
	}
	return $in;
}

sub writeFile
{
	my ($dosFileName, $content) = @_;
	if (open(OUT, "> $dosFileName"))
	{
		binmode(OUT);

		print OUT "$content";
		close(OUT);
		return $true;
	}
	else
	{
		return $false;
	}
}

sub instrument
{
	my ($in) = @_;

	my ($body, $trailing_white_spaces) = ($in =~ m/(.*\S)(\s*)$/gs);

	my $dateTimeJavaCode = "new java.text.SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss Z\").format(new java.util.Date())";
	my $methodName = "new Object(){}.getClass().getEnclosingMethod().getName()";

	$body =~ s/\r\n/NEWLINEBEGIN_CRLF_NEWLINEEND/gs;
	$body =~ s/\r/NEWLINEBEGIN_CR_NEWLINEEND/gs;
	$body =~ s/\n/NEWLINEBEGIN_LF_NEWLINEEND/gs;

	# Instrument method calls.
	# $1 = \n							(NEWLINEBEGIN_\w+)
	# $2 = leading white spaces			([ \t]+)
	# $3 = public, protected, private	(public|protected|private)
	# $4 = white spaces					([^\n"(]*\s+)
	# $5 = method name					([a-z_]\w+)
	#									(
	# $6 = parameter list				([^)]*)
	#									)
	# $7 = white space + {				([^{;]*{)
	# $8 = white space					(NEWLINEBEGIN_\w+\s+)
#print STDERR "input [$body]\n";
	# 2014-11-26

	# @PathParam("id")
	$body =~ s,\@PathParam\((.*?)\),\@PathParamXXX_PathParam_OPENPAREN_XXX$1XXX_PathParam_CLOSEPAREN_XXX ,g;

	# @SecuredParam("id")
	$body =~ s,\@SecuredParam\((.*?)\),\@SecuredParamXXX_SecuredParam_OPENPAREN_XXX$1XXX_SecuredParam_CLOSEPAREN_XXX ,g;

	# @QueryParam("id")
	$body =~ s,\@QueryParam\((.*?)\),\@QueryParamXXX_QueryParam_OPENPAREN_XXX$1XXX_QueryParam_CLOSEPAREN_XXX ,g;

	# @SuppressWarnings("id")
	$body =~ s,\@SuppressWarnings\((.*?)\),\@SuppressWarningsXXX_SuppressWarnings_OPENPAREN_XXX$1XXX_SuppressWarnings_CLOSEPAREN_XXX ,g;

	# @FormParam("id")
	$body =~ s,\@FormParam\((.*?)\),\@FormParamXXX_FormParam_OPENPAREN_XXX$1XXX_FormParam_CLOSEPAREN_XXX ,g;

	$body =~ s,(NEWLINEBEGIN_\w+)([ \t]+)(public|protected|private)([^\n"(]*\s+)([a-z_]\w+)\(([^)]*)\)([^{;]*{)(NEWLINEBEGIN_\w+\s+),"$1$2$3$4$5($6)$7try{System.out.println(($dateTimeJavaCode) + \" tid=\" + Thread.currentThread().getId() + \" \" + \"                                                                                                                                                      \".substring(0\, (Thread.currentThread().getStackTrace().length > $trimSpaces ? Thread.currentThread().getStackTrace().length - $trimSpaces : 0)) + \" $displayFileName(XXX_LINENO_XXX): $5(" . processColumns($6) . ") begins\");}catch(Exception xexexe){}$8",gse;

	# @PathParam("id")
	$body =~ s,XXX_PathParam_OPENPAREN_XXX,(,g;
	$body =~ s,XXX_PathParam_CLOSEPAREN_XXX,),g;

	# @SecuredParam("id")
	$body =~ s,XXX_SecuredParam_OPENPAREN_XXX,(,g;
	$body =~ s,XXX_SecuredParam_CLOSEPAREN_XXX,),g;

	# @QueryParam("id")
	$body =~ s,XXX_QueryParam_OPENPAREN_XXX,(,g;
	$body =~ s,XXX_QueryParam_CLOSEPAREN_XXX,),g;

	# @SuppressWarnings("id")
	$body =~ s,XXX_SuppressWarnings_OPENPAREN_XXX,(,g;
	$body =~ s,XXX_SuppressWarnings_CLOSEPAREN_XXX,),g;

	# @FormParam("id")
	$body =~ s,XXX_FormParam_OPENPAREN_XXX,(,g;
	$body =~ s,XXX_FormParam_CLOSEPAREN_XXX,),g;

	# $body =~ s,(NEWLINEBEGIN_\w+)([ \t]+)(public|protected|private)([^\n"(]*\s+)([a-z_]\w+)\((.*)\)([^{;]*{|throws)(NEWLINEBEGIN_\w+\s+),"$1$2$3$4$5($6)$7try{System.out.println(($dateTimeJavaCode) + \" tid=\" + Thread.currentThread().getId() + \" \" + \"                                                                                                                                                      \".substring(0\, (Thread.currentThread().getStackTrace().length > $trimSpaces ? Thread.currentThread().getStackTrace().length - $trimSpaces : 0)) + \" $displayFileName(XXX_LINENO_XXX): $5(" . processColumns($6) . ") begins\");}catch(Exception xexexe){}$8",gse;

	#
	# void getConnection() throws Exception {
	#
	#          $1                $2      $3$4           $5           $6       $7       $8
#	$body =~ s,(NEWLINEBEGIN_\w+)([ \t]+)()([^\n"(]*\s+)([a-z_]\w+)\(([^)]*)\)([^{;]*{)(NEWLINEBEGIN_\w+\s+),"$1$2$3$4$5($6)$7try{System.out.println(($dateTimeJavaCode) + \" tid=\" + Thread.currentThread().getId() + \" \" + \"                                                                                                                                                      \".substring(0\, (Thread.currentThread().getStackTrace().length > $trimSpaces ? Thread.currentThread().getStackTrace().length - $trimSpaces : 0)) + \" $displayFileName(XXX_LINENO_XXX): $5(" . processColumns($6) . ") begins\");}catch(Exception xexexe){}$8",gse;

#print STDERR "step 1 [$body]\n";
	# Instrument 'return's.
	$body =~ s/(NEWLINEBEGIN_\w+\s*)(return\s+)([\w\.]+);/$1 {try{System.out.println(($dateTimeJavaCode) + " tid=" + Thread.currentThread().getId() + " " + "                                                                                                                                                      ".substring(0, (Thread.currentThread().getStackTrace().length > $trimSpaces ? Thread.currentThread().getStackTrace().length - $trimSpaces : 0)) + " $displayFileName(XXX_LINENO_XXX): " + $methodName + "() returns '" + $3 + "'");}catch(Exception xexexe){} $2$3;}/gs;
#print STDERR "step 2 [$body]\n";
	my $out = "";

	# Update the line numbers.
	my @lines = split /_NEWLINEEND/, $body;
#print STDERR "step 3 [@lines]\n";
	for (my $i = 0; $i < @lines; $i++)
	{
		$lines[$i] =~ s/XXX_LINENO_XXX/$i + 1/ge;
	}

	$out = join("", @lines);

	$out =~ s/NEWLINEBEGIN_CRLF/\r\n/gs;
	$out =~ s/NEWLINEBEGIN_CR/\r/gs;
	$out =~ s/NEWLINEBEGIN_LF/\n/gs;

	$out =~ s/\s+$//gs;
	$out .= $trailing_white_spaces;

	return $out;
}

$cwd = getcwd();
$dosFileName = @ARGV[0];
$dosFileName =~ s/$cwd//g;
$dosFileName =~ s,^/,,g;
($unixFileName = $dosFileName) =~ s/\\/\//g;
my ($baseName) = ($cwd =~ m/.*[\\\/](.*)/);
$displayFileName = "$baseName/$unixFileName";

if ($dosFileName =~ m/.*\.java$/)
{
	if ($in = readFile($dosFileName))
	{
		$out = instrument($in);
		if ($in ne $out)
		{
			if (writeFile($dosFileName, $out) == $true)
			{
				print STDERR "Instrumented $dosFileName " . length($in) . " => " . length($out) . "\n";
			}
		}
		else
		{
			# print STDERR "No change $dosFileName " . length($in) . " " . length($out) . "\n";
		}
	}
	else
	{
		printf STDERR "instrument-java.pl: ERR> failed to open $dosFileName\n";
	}
} else {
#	printf STDERR "instrument-java.pl: Not a java file: $dosFileName\n";
}
