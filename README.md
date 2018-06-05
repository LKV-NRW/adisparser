# AdisParser

A parser to allow to read adis files with ease and consequently work with the data contained in the adis file.

## Getting started

This project can be used as a library in any java (or similar) project. To add it into your dependencies use one of the following ways.

Add dependency via Maven:
```xml
<dependency>
    <groupId>de.lkv.nrw</groupId>
    <artifactId>adisparser</artifactId>
    <version>1.0.0</version>
</dependency>
```

Add dependency via Gradle:
```java
compile(group: 'de.lkv.nrw', name: 'adisparser', version: '1.0.0')
```

> Please note that this project is currently not available on any repository. To add it to you local repository download this project and 'mvn install' it to your local repository.

## How to use

There are currently three ways of parsing adis data.

### Parse an adis file

```java
LinkedList<AdisLine> returnedList = AdisParser.readFile(new File("anAdisFile.ads"));
```

or with encoding (default is 'ISO-8859-15')

```java
LinkedList<AdisLine> returnedList = AdisParser.readFile(new File("myAdisFile.ads"), "UTF-8");
```

### Parse a list of adis lines

```java
LinkedList<AdisLine> returnedList = AdisParser.readFile(myList);
```

> you'll want to make sure that the list is of an ordered manner.

### Parse a single adis line at a time

```java
AdisLine returnedLine = AdisLine.parse(myLine);
```

> Parsing value lines by itself will cause an error since a value line must be followed by a definition line. So make sure to first parse a definition line.

### Getting the data

The class AdisLine represents a common class that represents any type of adis line. To take advantage of the specific features and methods of a specific adis line, say a value line (VN...), you'll have to cast the line to a AdisValueLine.

```java
if(returnedLine instanceof AdisValueLine) {
    AdisValueLine valLine = (AdisValueLine) returnedLine;

    // do stuff here...
}
```

### Line types

This project distinguishes between five different adis line types, these are:

* AdisDefinitionLine (DH, DN)
* AdisValueLine (VH, VN)
* AdisPropertyLine (PO, PN, QO, QN, QR)
* AdisCommandLine (TN, EN, ZN)
* AdisCommentLine (CN, CF)

The listed classes all extend AdisLine and contain specific methods and functionality for these type of lines.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/LKV-NRW/adisparser/tags).

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details