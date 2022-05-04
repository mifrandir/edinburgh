Assignment Part I - Submission

# Compile Alone

Command: `javac *.java`
Score: 5.0 / 5.0
Output:
```

```

Errors:
```

```

# Did your submitted code compile on its own?

Passed!

# Compile with Basic Tests

Command: `cp $FIXTURES/*BasicTest.java $FIXTURES/OutputFormatter.java $FIXTURES/TestMain.java $FIXTURES/*.txt . && cp $FIXTURES/*.jar . && javac -cp ./junit-4.12.jar:. *.java`
Score: 5.0 / 5.0
Output:
```

```

Errors:
```

```

# Did your program compile with the Basic Tests?

Passed!

# Run Basic Tests

Command: `java -cp .:./hamcrest-core-1.3.jar:./junit-4.12.jar TestMain FoxHoundUtilsBasicTest FoxHoundIOBasicTest FoxHoundUIBasicTest`
Score: 0.0 / 45.0
Output:
```
Running tests for [class FoxHoundUtilsBasicTest, class FoxHoundIOBasicTest, class FoxHoundUIBasicTest]

6813a0f9-5afa-49ff-9ac7-e7e43180e4fa START
Running test: testIsHoundWinDInvalid [PASSED]
Running test: testIsValidMoveDestOccupied [PASSED]
Running test: testIsValidMovePositive [PASSED]
Running test: testIsFoxWinNull [PASSED]
Running test: testIsValidMoveInvalidFigureAtOrigin [PASSED]
Running test: testInitialisePositionsNegativeDim [PASSED]
Running test: testIsFoxWinPositive [PASSED]
Running test: testInitialisePositionsDefaultDim [PASSED]
Running test: testIsFoxWinNegative [PASSED]
Running test: testIsValidMoveEmptyOrigin [PASSED]
Running test: testIsHoundWinPositive [PASSED]
Running test: testIsHoundWinNegative [PASSED]
Running test: testIsHoundWinPNull [PASSED]
Running test: testLoadGameInvalidFile [PASSED]
Running test: testLoadGameValidInput [FAILED]
Running test: testSaveGamePathNull [PASSED]
Running test: testLoadGamePlayersNonDefaultDim [PASSED]
Running test: testLoadGameInvalidFileContent [FAILED]
Running test: testSaveGamePlayersNonDefaultDim [PASSED]
Running test: testSaveGameInvalidPath [PASSED]
Running test: testLoadGamePathNull [PASSED]
Running test: testSaveGameValidInput [PASSED]
Running test: testDisplayBoard [PASSED]
Running test: testPositionQueryMessage [PASSED]
Running test: testPositionQueryError [PASSED]
Provide origin and destination coordinates.
Enter two positions between A1-H8:

Running test: testPositionQueryReturn [PASSED]
Enter the path: 

```

Errors:
```
Move is expected to be invaid if the given destination is already occupied.
Move is expected to be invaid if the given destination is already occupied.
Move is expected to be invaid if the given destination is already occupied.
Move is expected to be invalid if the figure in the origin does not match the given figure type.
Move is expected to be invalid if the figure in the origin does not match the given figure type.
java.io.IOException: No such file or directory
	at java.base/java.io.UnixFileSystem.createFileExclusively(Native Method)
	at java.base/java.io.File.createNewFile(File.java:1026)
	at FoxHoundUI.onlyload(FoxHoundUI.java:103)
	at FoxHoundUI.fileQuery(FoxHoundUI.java:69)
	at FoxHoundUIBasicTest.testFileQueryReturn(FoxHoundUIBasicTest.java:238)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:566)
	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
	at org.junit.internal.runners.statements.RunBefores.evaluate(RunBefores.java:26)
	at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
	at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
	at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
	at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
	at org.junit.runners.Suite.runChild(Suite.java:128)
	at org.junit.runners.Suite.runChild(Suite.java:27)
	at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
	at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
	at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
	at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
	at org.junit.runner.JUnitCore.run(JUnitCore.java:115)
	at org.junit.runner.JUnitCore.run(JUnitCore.java:105)
	at org.junit.runner.JUnitCore.run(JUnitCore.java:94)
	at TestMain.main(TestMain.java:41)

```

# Compile with Advanced Tests

Command: `cp $FIXTURES/*AdvancedTest.java . && mkdir -p testFolder && javac -cp ./junit-4.12.jar:. *.java`
Score: 5.0 / 5.0
Output:
```

```

Errors:
```

```

# Run Advanced Tests

Command: `java -cp .:./hamcrest-core-1.3.jar:./junit-4.12.jar TestMain FoxHoundUtilsAdvancedTest FoxHoundIOAdvancedTest FoxHoundUIAdvancedTest`
Score: 0.0 / 40.0
Output:
```
Running tests for [class FoxHoundUtilsAdvancedTest, class FoxHoundIOAdvancedTest, class FoxHoundUIAdvancedTest]

a3d24f93-78e4-410f-9493-2dde85bd6111 START
Running test: testInitialisePositionsTooSmallDim [FAILED]
Running test: testIsHoundWinPositiveCornerBlockBottomLeft [PASSED]
Running test: testIsValidMoveDestInvalidBoardCoord1 [PASSED]
Running test: testIsValidMoveDestInvalidBoardCoord2 [FAILED]
Running test: testIsValidMoveDestInvalidBoardCoord3 [PASSED]
Running test: testIsHoundWinInvalidDimension1 [FAILED]
Running test: testIsHoundWinInvalidDimension2 [PASSED]
Running test: testIsHoundWinInvalidDimension3 [FAILED]
Running test: testInitialisePositionsMinField [PASSED]
Running test: testIsValidMoveInvalidBoardSetup [FAILED]
Running test: testIsValidMoveDestTooFar [PASSED]
Running test: testIsValidMoveDestNull [PASSED]
Running test: testIsValidMovePlayersInvalidBoardCoord1 [PASSED]
Running test: testIsValidMovePlayersInvalidBoardCoord2 [PASSED]
Running test: testIsValidMovePlayersInvalidBoardCoord3 [FAILED]
Running test: testIsValidMovePlayersInvalidBoardCoord4 [PASSED]
Running test: testIsValidMovePositiveNonDefaultDimension [FAILED]
Running test: testIsValidMoveInvalidFigure [FAILED]
Running test: testInitialisePositionsEvenDimOddHalf [FAILED]
Running test: testIsValidMoveHoundBackwards [PASSED]
Running test: testIsHoundWinInvalidBoardSetup [FAILED]
Running test: testInitialisePositionsOddDimEvenHalf [FAILED]
Running test: testIsValidMoveSameOriginAndDest [PASSED]
Running test: testIsHoundWinPositiveWallBlockRight [PASSED]
Running test: testInitialisePositionsZeroDim [PASSED]
Running test: testInitialisePositionsMaxField [FAILED]
Running test: testIsValidMoveOriginNull [PASSED]
Running test: testIsValidMovePlayersNull [FAILED]
Running test: testInitialisePositionsOddDimOddHalf [PASSED]
Running test: testIsFoxWinInvalidBoardCoord1 [FAILED]
Running test: testIsFoxWinInvalidBoardCoord2 [FAILED]
Running test: testIsFoxWinInvalidBoardCoord3 [FAILED]
Running test: testIsHoundWinInvalidBoardCoord1 [PASSED]
Running test: testIsHoundWinInvalidBoardCoord2 [PASSED]
Running test: testIsHoundWinInvalidBoardCoord3 [FAILED]
Running test: testIsHoundWinInvalidBoardCoord4 [PASSED]
Running test: testIsHoundWinPositiveWallBlockLeft [PASSED]
Running test: testInitialisePositionsEvenDimEvenHalf [PASSED]
Running test: testInitialisePositionsTooLargeDim [FAILED]
Running test: testIsFoxWinPositiveNonDefaultDim [FAILED]
Running test: testIsValidMoveOriginInvalidBoardCoord1 [PASSED]
Running test: testIsValidMoveOriginInvalidBoardCoord2 [FAILED]
Running test: testIsValidMoveOriginInvalidBoardCoord3 [PASSED]
Running test: testIsHoundWinPositiveNonDefaultDim [FAILED]
Running test: testIsFoxWinNegativeNonDefaultDim [PASSED]
Running test: testIsValidMoveInvalidDimension1 [FAILED]
Running test: testIsValidMoveInvalidDimension2 [PASSED]
Running test: testIsValidMoveInvalidDimension3 [PASSED]
Running test: testIsValidMoveInvalidDimension4 [FAILED]
Running test: testIsHoundWinPositiveWallBlockBottom [PASSED]
Running test: testIsHoundWinNegativeNonDefaultDim [FAILED]
Running test: testLoadGameInvalidBoardCoord1 [FAILED]
Running test: testLoadGameInvalidBoardCoord2 [FAILED]
Running test: testLoadGameInvalidBoardCoord3 [FAILED]
Running test: testLoadGameInvalidBoardCoord4 [FAILED]
Running test: testLoadGamePlayersNull [PASSED]
Running test: testSaveGameInvalidMove [FAILED]
Error
Running test: testLoadGameFileIsFolder [FAILED]
Running test: testSaveGamePlayersNull [PASSED]
Running test: testSaveGameInvalidBoardCoord1 [FAILED]
Running test: testSaveGameInvalidBoardCoord2 [FAILED]
Running test: testSaveGameInvalidBoardCoord3 [FAILED]
Running test: testSaveGameInvalidBoardCoord4 [FAILED]
Running test: testSaveGamePathExists [FAILED]
Running test: testPositionQueryScannerNull [PASSED]
Provide origin and destination coordinates.
Enter two positions between A1-H8:

Provide origin and destination coordinates.
Enter two positions between A1-H8:

Running test: testPositionQueryInvalidDimension1 [FAILED]
Provide origin and destination coordinates.
Enter two positions between A1-H8:

Running test: testPositionQueryInvalidDimension2 [PASSED]
Provide origin and destination coordinates.
Enter two positions between A1-H8:

Running test: testPositionQueryInvalidDimension3 [FAILED]
Provide origin and destination coordinates.
Enter two positions between A1-H8:

Running test: testPositionQueryInvalidDimension4 [FAILED]
Running test: testDisplayBoardLargeDim [PASSED]
Running test: testPositionQuerySmallBoardMessage [FAILED]
  
AB  

1 FH 1
2 .. 2

  ABRunning test: testDisplayBoardInvalidDimension1 [FAILED]
Running test: testDisplayBoardInvalidDimension2 [PASSED]
Running test: testDisplayBoardInvalidDimension3 [PASSED]
Running test: testDisplayBoardInvalidDimension4 [FAILED]
Running test: testDisplayBoardSmallDim [PASSED]
Running test: testPositionQueryInvalidInput1 [PASSED]
Running test: testPositionQueryInvalidInput2 [FAILED]
Running test: testPositionQueryLargeBoardMessage [FAILED]
Running test: testDisplayBoardPlayersNull [FAILED]
Running test: testDisplayBoardPlayersInvalidBoardCoord1 [PASSED]
Running test: testDisplayBoardPlayersInvalidBoardCoord2 [PASSED]
  
ABCDEFGH  

1 .H...H.H 1
2 ........ 2
3 H....... 3
4 ........ 4
5 ........ 5
6 ........ 6
7 ........ 7
8 ....F... 8

  ABCDEFGHRunning test: testDisplayBoardPlayersInvalidBoardCoord3 [FAILED]
Running test: testDisplayBoardPlayersInvalidBoardCoord4 [PASSED]
  
ABCDE  

1 FH.H. 1
2 ..... 2
3 ..... 3
4 ..... 4
5 ..... 5

  ABCDERunning test: testDisplayBoardInvalidBoardSetup [FAILED]
Running test: testDisplayBoardLeadingZero [FAILED]
Enter the path: 

```

Errors:
```
Move is expected to be invaid if the given destination is already occupied.
Move is expected to be invalid if the figure in the origin does not match the given figure type.
Loaded player array not as expected.

```

