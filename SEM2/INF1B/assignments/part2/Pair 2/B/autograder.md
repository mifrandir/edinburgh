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
Score: 45.0 / 45.0
Output:
```
Running tests for [class FoxHoundUtilsBasicTest, class FoxHoundIOBasicTest, class FoxHoundUIBasicTest]

72541cc9-19d5-4a64-9b7f-0833f0965e35 START
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
Running test: testLoadGameValidInput [PASSED]
Running test: testSaveGamePathNull [PASSED]
Running test: testLoadGamePlayersNonDefaultDim [PASSED]
Running test: testLoadGameInvalidFileContent [PASSED]
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

Enter file path:

Running test: testFileQueryReturn [PASSED]
Running test: testFileQueryMessage [PASSED]

Time: 0.266

 (28 tests)
72541cc9-19d5-4a64-9b7f-0833f0965e35
END 1.0

```

Errors:
```

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
Score: 27.586208 / 40.0
Output:
```
Running tests for [class FoxHoundUtilsAdvancedTest, class FoxHoundIOAdvancedTest, class FoxHoundUIAdvancedTest]

0b01e967-ec68-4239-8aa2-f24b021e0d0e START
Running test: testInitialisePositionsTooSmallDim [PASSED]
Running test: testIsHoundWinPositiveCornerBlockBottomLeft [FAILED]
Running test: testIsValidMoveDestInvalidBoardCoord1 [FAILED]
Running test: testIsValidMoveDestInvalidBoardCoord2 [FAILED]
Running test: testIsValidMoveDestInvalidBoardCoord3 [FAILED]
Running test: testIsHoundWinInvalidDimension1 [PASSED]
Running test: testIsHoundWinInvalidDimension2 [PASSED]
Running test: testIsHoundWinInvalidDimension3 [PASSED]
Running test: testInitialisePositionsMinField [PASSED]
Running test: testIsValidMoveInvalidBoardSetup [FAILED]
Running test: testIsValidMoveDestTooFar [PASSED]
Running test: testIsValidMoveDestNull [PASSED]
Running test: testIsValidMovePlayersInvalidBoardCoord1 [PASSED]
Running test: testIsValidMovePlayersInvalidBoardCoord2 [FAILED]
Running test: testIsValidMovePlayersInvalidBoardCoord3 [FAILED]
Running test: testIsValidMovePlayersInvalidBoardCoord4 [FAILED]
Running test: testIsValidMovePositiveNonDefaultDimension [FAILED]
Running test: testIsValidMoveInvalidFigure [PASSED]
Running test: testInitialisePositionsEvenDimOddHalf [FAILED]
Running test: testIsValidMoveHoundBackwards [PASSED]
Running test: testIsHoundWinInvalidBoardSetup [PASSED]
Running test: testInitialisePositionsOddDimEvenHalf [FAILED]
Running test: testIsValidMoveSameOriginAndDest [PASSED]
Running test: testIsHoundWinPositiveWallBlockRight [FAILED]
Running test: testInitialisePositionsZeroDim [PASSED]
Running test: testInitialisePositionsMaxField [FAILED]
Running test: testIsValidMoveOriginNull [PASSED]
Running test: testIsValidMovePlayersNull [PASSED]
Running test: testInitialisePositionsOddDimOddHalf [FAILED]
Running test: testIsFoxWinInvalidBoardCoord1 [FAILED]
Running test: testIsFoxWinInvalidBoardCoord2 [FAILED]
Running test: testIsFoxWinInvalidBoardCoord3 [FAILED]
Running test: testIsHoundWinInvalidBoardCoord1 [PASSED]
Running test: testIsHoundWinInvalidBoardCoord2 [PASSED]
Running test: testIsHoundWinInvalidBoardCoord3 [PASSED]
Running test: testIsHoundWinInvalidBoardCoord4 [PASSED]
Running test: testIsHoundWinPositiveWallBlockLeft [FAILED]
Running test: testInitialisePositionsEvenDimEvenHalf [FAILED]
Running test: testInitialisePositionsTooLargeDim [PASSED]
Running test: testIsFoxWinPositiveNonDefaultDim [PASSED]
Running test: testIsValidMoveOriginInvalidBoardCoord1 [FAILED]
Running test: testIsValidMoveOriginInvalidBoardCoord2 [FAILED]
Running test: testIsValidMoveOriginInvalidBoardCoord3 [FAILED]
Running test: testIsHoundWinPositiveNonDefaultDim [FAILED]
Running test: testIsFoxWinNegativeNonDefaultDim [PASSED]
Running test: testIsValidMoveInvalidDimension1 [PASSED]
Running test: testIsValidMoveInvalidDimension2 [PASSED]
Running test: testIsValidMoveInvalidDimension3 [PASSED]
Running test: testIsValidMoveInvalidDimension4 [PASSED]
Running test: testIsHoundWinPositiveWallBlockBottom [PASSED]
Running test: testIsHoundWinNegativeNonDefaultDim [FAILED]
Running test: testLoadGameInvalidBoardCoord1 [PASSED]
Running test: testLoadGameInvalidBoardCoord2 [PASSED]
Running test: testLoadGameInvalidBoardCoord3 [PASSED]
Running test: testLoadGameInvalidBoardCoord4 [PASSED]
Running test: testLoadGamePlayersNull [PASSED]
Running test: testSaveGameInvalidMove [PASSED]
Running test: testLoadGameFileIsFolder [FAILED]
Running test: testSaveGamePlayersNull [PASSED]
Running test: testSaveGameInvalidBoardCoord1 [PASSED]
Running test: testSaveGameInvalidBoardCoord2 [PASSED]
Running test: testSaveGameInvalidBoardCoord3 [PASSED]
Running test: testSaveGameInvalidBoardCoord4 [PASSED]
Running test: testSaveGamePathExists [PASSED]
Running test: testPositionQueryScannerNull [PASSED]
Running test: testPositionQueryInvalidDimension1 [PASSED]
Running test: testPositionQueryInvalidDimension2 [PASSED]
Running test: testPositionQueryInvalidDimension3 [PASSED]
Running test: testPositionQueryInvalidDimension4 [PASSED]
Running test: testDisplayBoardLargeDim [PASSED]
Running test: testPositionQuerySmallBoardMessage [PASSED]
Running test: testDisplayBoardInvalidDimension1 [PASSED]
Running test: testDisplayBoardInvalidDimension2 [PASSED]
Running test: testDisplayBoardInvalidDimension3 [PASSED]
Running test: testDisplayBoardInvalidDimension4 [PASSED]
Running test: testDisplayBoardSmallDim [PASSED]
Running test: testPositionQueryInvalidInput1 [PASSED]
Running test: testPositionQueryInvalidInput2 [PASSED]
Running test: testPositionQueryLargeBoardMessage [FAILED]
Running test: testDisplayBoardPlayersNull [PASSED]
Running test: testDisplayBoardPlayersInvalidBoardCoord1 [PASSED]
Running test: testDisplayBoardPlayersInvalidBoardCoord2 [PASSED]
Running test: testDisplayBoardPlayersInvalidBoardCoord3 [PASSED]
Running test: testDisplayBoardPlayersInvalidBoardCoord4 [PASSED]
Running test: testDisplayBoardInvalidBoardSetup [PASSED]
Running test: testDisplayBoardLeadingZero [FAILED]
Running test: testFileQueryScannerNull [PASSED]

Time: 3.286
27 failure(s)
1) testIsHoundWinPositiveCornerBlockBottomLeft(FoxHoundUtilsAdvancedTest): Hounds are expected to win in given positions: [B1, B7, F1, H1, A8]
java.lang.AssertionError: Hounds are expected to win in given positions: [B1, B7, F1, H1, A8]
	at org.junit.Assert.fail(Assert.java:88)
	at org.junit.Assert.assertTrue(Assert.java:41)
	at FoxHoundUtilsAdvancedTest.testIsHoundWinPositiveCornerBlockBottomLeft(FoxHoundUtilsAdvancedTest.java:219)
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
2) testIsValidMoveDestInvalidBoardCoord1(FoxHoundUtilsAdvancedTest): Expected exception: java.lang.IllegalArgumentException
java.lang.AssertionError: Expected exception: java.lang.IllegalArgumentException
	at org.junit.internal.runners.statements.ExpectException.evaluate(ExpectException.java:32)
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
3) testIsValidMoveDestInvalidBoardCoord2(FoxHoundUtilsAdvancedTest): Expected exception: java.lang.IllegalArgumentException
java.lang.AssertionError: Expected exception: java.lang.IllegalArgumentException
	at org.junit.internal.runners.statements.ExpectException.evaluate(ExpectException.java:32)
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
4) testIsValidMoveDestInvalidBoardCoord3(FoxHoundUtilsAdvancedTest): Expected exception: java.lang.IllegalArgumentException
java.lang.AssertionError: Expected exception: java.lang.IllegalArgumentException
	at org.junit.internal.runners.statements.ExpectException.evaluate(ExpectException.java:32)
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
5) testIsValidMoveInvalidBoardSetup(FoxHoundUtilsAdvancedTest): Expected exception: java.lang.IllegalArgumentException
java.lang.AssertionError: Expected exception: java.lang.IllegalArgumentException
	at org.junit.internal.runners.statements.ExpectException.evaluate(ExpectException.java:32)
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
6) testIsValidMovePlayersInvalidBoardCoord2(FoxHoundUtilsAdvancedTest): Expected exception: java.lang.IllegalArgumentException
java.lang.AssertionError: Expected exception: java.lang.IllegalArgumentException
	at org.junit.internal.runners.statements.ExpectException.evaluate(ExpectException.java:32)
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
7) testIsValidMovePlayersInvalidBoardCoord3(FoxHoundUtilsAdvancedTest): Expected exception: java.lang.IllegalArgumentException
java.lang.AssertionError: Expected exception: java.lang.IllegalArgumentException
	at org.junit.internal.runners.statements.ExpectException.evaluate(ExpectException.java:32)
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
8) testIsValidMovePlayersInvalidBoardCoord4(FoxHoundUtilsAdvancedTest): Expected exception: java.lang.IllegalArgumentException
java.lang.AssertionError: Expected exception: java.lang.IllegalArgumentException
	at org.junit.internal.runners.statements.ExpectException.evaluate(ExpectException.java:32)
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
9) testIsValidMovePositiveNonDefaultDimension(FoxHoundUtilsAdvancedTest): Move (E10 F9) is expected to be valid for setup: [B1, D1, F1, H1, J1, E10]
java.lang.AssertionError: Move (E10 F9) is expected to be valid for setup: [B1, D1, F1, H1, J1, E10]
	at org.junit.Assert.fail(Assert.java:88)
	at org.junit.Assert.assertTrue(Assert.java:41)
	at FoxHoundUtilsAdvancedTest.checkMove(FoxHoundUtilsAdvancedTest.java:314)
	at FoxHoundUtilsAdvancedTest.testIsValidMovePositiveNonDefaultDimension(FoxHoundUtilsAdvancedTest.java:327)
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
10) testInitialisePositionsEvenDimOddHalf(FoxHoundUtilsAdvancedTest): Returned positions not as expected for dimension 10: arrays first differed at element [0]; expected:<B[]1> but was:<B[0]1>
Returned positions not as expected for dimension 10: arrays first differed at element [0]; expected:<B[]1> but was:<B[0]1>
	at org.junit.internal.ComparisonCriteria.arrayEquals(ComparisonCriteria.java:55)
	at org.junit.Assert.internalArrayEquals(Assert.java:532)
	at org.junit.Assert.assertArrayEquals(Assert.java:283)
	at FoxHoundUtilsAdvancedTest.checkInitPositions(FoxHoundUtilsAdvancedTest.java:56)
	at FoxHoundUtilsAdvancedTest.testInitialisePositionsEvenDimOddHalf(FoxHoundUtilsAdvancedTest.java:97)
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
Caused by: org.junit.ComparisonFailure: expected:<B[]1> but was:<B[0]1>
	at org.junit.Assert.assertEquals(Assert.java:115)
	at org.junit.Assert.assertEquals(Assert.java:144)
	at org.junit.internal.ExactComparisonCriteria.assertElementsEqual(ExactComparisonCriteria.java:8)
	at org.junit.internal.ComparisonCriteria.arrayEquals(ComparisonCriteria.java:53)
	... 35 more
11) testInitialisePositionsOddDimEvenHalf(FoxHoundUtilsAdvancedTest): Returned positions not as expected for dimension 13: arrays first differed at element [0]; expected:<B[]1> but was:<B[0]1>
Returned positions not as expected for dimension 13: arrays first differed at element [0]; expected:<B[]1> but was:<B[0]1>
	at org.junit.internal.ComparisonCriteria.arrayEquals(ComparisonCriteria.java:55)
	at org.junit.Assert.internalArrayEquals(Assert.java:532)
	at org.junit.Assert.assertArrayEquals(Assert.java:283)
	at FoxHoundUtilsAdvancedTest.checkInitPositions(FoxHoundUtilsAdvancedTest.java:56)
	at FoxHoundUtilsAdvancedTest.testInitialisePositionsOddDimEvenHalf(FoxHoundUtilsAdvancedTest.java:73)
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
Caused by: org.junit.ComparisonFailure: expected:<B[]1> but was:<B[0]1>
	at org.junit.Assert.assertEquals(Assert.java:115)
	at org.junit.Assert.assertEquals(Assert.java:144)
	at org.junit.internal.ExactComparisonCriteria.assertElementsEqual(ExactComparisonCriteria.java:8)
	at org.junit.internal.ComparisonCriteria.arrayEquals(ComparisonCriteria.java:53)
	... 35 more
12) testIsHoundWinPositiveWallBlockRight(FoxHoundUtilsAdvancedTest): Hounds are expected to win in given positions: [B1, D1, G4, G2, H3]
java.lang.AssertionError: Hounds are expected to win in given positions: [B1, D1, G4, G2, H3]
	at org.junit.Assert.fail(Assert.java:88)
	at org.junit.Assert.assertTrue(Assert.java:41)
	at FoxHoundUtilsAdvancedTest.testIsHoundWinPositiveWallBlockRight(FoxHoundUtilsAdvancedTest.java:186)
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
13) testInitialisePositionsMaxField(FoxHoundUtilsAdvancedTest): Returned positions not as expected for dimension 26: arrays first differed at element [0]; expected:<B[]1> but was:<B[0]1>
Returned positions not as expected for dimension 26: arrays first differed at element [0]; expected:<B[]1> but was:<B[0]1>
	at org.junit.internal.ComparisonCriteria.arrayEquals(ComparisonCriteria.java:55)
	at org.junit.Assert.internalArrayEquals(Assert.java:532)
	at org.junit.Assert.assertArrayEquals(Assert.java:283)
	at FoxHoundUtilsAdvancedTest.checkInitPositions(FoxHoundUtilsAdvancedTest.java:56)
	at FoxHoundUtilsAdvancedTest.testInitialisePositionsMaxField(FoxHoundUtilsAdvancedTest.java:66)
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
Caused by: org.junit.ComparisonFailure: expected:<B[]1> but was:<B[0]1>
	at org.junit.Assert.assertEquals(Assert.java:115)
	at org.junit.Assert.assertEquals(Assert.java:144)
	at org.junit.internal.ExactComparisonCriteria.assertElementsEqual(ExactComparisonCriteria.java:8)
	at org.junit.internal.ComparisonCriteria.arrayEquals(ComparisonCriteria.java:53)
	... 35 more
14) testInitialisePositionsOddDimOddHalf(FoxHoundUtilsAdvancedTest): Returned positions not as expected for dimension 11: arrays first differed at element [0]; expected:<B[]1> but was:<B[0]1>
Returned positions not as expected for dimension 11: arrays first differed at element [0]; expected:<B[]1> but was:<B[0]1>
	at org.junit.internal.ComparisonCriteria.arrayEquals(ComparisonCriteria.java:55)
	at org.junit.Assert.internalArrayEquals(Assert.java:532)
	at org.junit.Assert.assertArrayEquals(Assert.java:283)
	at FoxHoundUtilsAdvancedTest.checkInitPositions(FoxHoundUtilsAdvancedTest.java:56)
	at FoxHoundUtilsAdvancedTest.testInitialisePositionsOddDimOddHalf(FoxHoundUtilsAdvancedTest.java:81)
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
Caused by: org.junit.ComparisonFailure: expected:<B[]1> but was:<B[0]1>
	at org.junit.Assert.assertEquals(Assert.java:115)
	at org.junit.Assert.assertEquals(Assert.java:144)
	at org.junit.internal.ExactComparisonCriteria.assertElementsEqual(ExactComparisonCriteria.java:8)
	at org.junit.internal.ComparisonCriteria.arrayEquals(ComparisonCriteria.java:53)
	... 35 more
15) testIsFoxWinInvalidBoardCoord1(FoxHoundUtilsAdvancedTest): Expected exception: java.lang.IllegalArgumentException
java.lang.AssertionError: Expected exception: java.lang.IllegalArgumentException
	at org.junit.internal.runners.statements.ExpectException.evaluate(ExpectException.java:32)
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
16) testIsFoxWinInvalidBoardCoord2(FoxHoundUtilsAdvancedTest): Expected exception: java.lang.IllegalArgumentException
java.lang.AssertionError: Expected exception: java.lang.IllegalArgumentException
	at org.junit.internal.runners.statements.ExpectException.evaluate(ExpectException.java:32)
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
17) testIsFoxWinInvalidBoardCoord3(FoxHoundUtilsAdvancedTest): Expected exception: java.lang.IllegalArgumentException
java.lang.AssertionError: Expected exception: java.lang.IllegalArgumentException
	at org.junit.internal.runners.statements.ExpectException.evaluate(ExpectException.java:32)
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
18) testIsHoundWinPositiveWallBlockLeft(FoxHoundUtilsAdvancedTest): Hounds are expected to win in given positions: [B1, B3, F1, H1, A2]
java.lang.AssertionError: Hounds are expected to win in given positions: [B1, B3, F1, H1, A2]
	at org.junit.Assert.fail(Assert.java:88)
	at org.junit.Assert.assertTrue(Assert.java:41)
	at FoxHoundUtilsAdvancedTest.testIsHoundWinPositiveWallBlockLeft(FoxHoundUtilsAdvancedTest.java:169)
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
19) testInitialisePositionsEvenDimEvenHalf(FoxHoundUtilsAdvancedTest): Returned positions not as expected for dimension 12: arrays first differed at element [0]; expected:<B[]1> but was:<B[0]1>
Returned positions not as expected for dimension 12: arrays first differed at element [0]; expected:<B[]1> but was:<B[0]1>
	at org.junit.internal.ComparisonCriteria.arrayEquals(ComparisonCriteria.java:55)
	at org.junit.Assert.internalArrayEquals(Assert.java:532)
	at org.junit.Assert.assertArrayEquals(Assert.java:283)
	at FoxHoundUtilsAdvancedTest.checkInitPositions(FoxHoundUtilsAdvancedTest.java:56)
	at FoxHoundUtilsAdvancedTest.testInitialisePositionsEvenDimEvenHalf(FoxHoundUtilsAdvancedTest.java:89)
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
Caused by: org.junit.ComparisonFailure: expected:<B[]1> but was:<B[0]1>
	at org.junit.Assert.assertEquals(Assert.java:115)
	at org.junit.Assert.assertEquals(Assert.java:144)
	at org.junit.internal.ExactComparisonCriteria.assertElementsEqual(ExactComparisonCriteria.java:8)
	at org.junit.internal.ComparisonCriteria.arrayEquals(ComparisonCriteria.java:53)
	... 35 more
20) testIsValidMoveOriginInvalidBoardCoord1(FoxHoundUtilsAdvancedTest): Expected exception: java.lang.IllegalArgumentException
java.lang.AssertionError: Expected exception: java.lang.IllegalArgumentException
	at org.junit.internal.runners.statements.ExpectException.evaluate(ExpectException.java:32)
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
21) testIsValidMoveOriginInvalidBoardCoord2(FoxHoundUtilsAdvancedTest): Expected exception: java.lang.IllegalArgumentException
java.lang.AssertionError: Expected exception: java.lang.IllegalArgumentException
	at org.junit.internal.runners.statements.ExpectException.evaluate(ExpectException.java:32)
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
22) testIsValidMoveOriginInvalidBoardCoord3(FoxHoundUtilsAdvancedTest): Expected exception: java.lang.IllegalArgumentException
java.lang.AssertionError: Expected exception: java.lang.IllegalArgumentException
	at org.junit.internal.runners.statements.ExpectException.evaluate(ExpectException.java:32)
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
23) testIsHoundWinPositiveNonDefaultDim(FoxHoundUtilsAdvancedTest): ERROR: A players coordinate is not on the board
java.lang.IllegalArgumentException: ERROR: A players coordinate is not on the board
	at FoxHoundUtils.isHoundWin(FoxHoundUtils.java:195)
	at FoxHoundUtilsAdvancedTest.testIsHoundWinPositiveNonDefaultDim(FoxHoundUtilsAdvancedTest.java:229)
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
24) testIsHoundWinNegativeNonDefaultDim(FoxHoundUtilsAdvancedTest): ERROR: A players coordinate is not on the board
java.lang.IllegalArgumentException: ERROR: A players coordinate is not on the board
	at FoxHoundUtils.isHoundWin(FoxHoundUtils.java:195)
	at FoxHoundUtilsAdvancedTest.testIsHoundWinNegativeNonDefaultDim(FoxHoundUtilsAdvancedTest.java:240)
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
25) testLoadGameFileIsFolder(FoxHoundIOAdvancedTest): String index out of range: 0
java.lang.StringIndexOutOfBoundsException: String index out of range: 0
	at java.base/java.lang.StringLatin1.charAt(StringLatin1.java:47)
	at java.base/java.lang.String.charAt(String.java:693)
	at FoxHoundIO.loadGame(FoxHoundIO.java:61)
	at FoxHoundIOAdvancedTest.checkLoadedData(FoxHoundIOAdvancedTest.java:26)
	at FoxHoundIOAdvancedTest.testLoadGameFileIsFolder(FoxHoundIOAdvancedTest.java:40)
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
26) testPositionQueryLargeBoardMessage(FoxHoundUIAdvancedTest): Java heap space
java.lang.OutOfMemoryError: Java heap space
	at java.base/java.util.Arrays.copyOf(Arrays.java:3745)
	at java.base/java.io.ByteArrayOutputStream.grow(ByteArrayOutputStream.java:120)
	at java.base/java.io.ByteArrayOutputStream.ensureCapacity(ByteArrayOutputStream.java:95)
	at java.base/java.io.ByteArrayOutputStream.write(ByteArrayOutputStream.java:156)
	at java.base/java.io.PrintStream.write(PrintStream.java:559)
	at java.base/sun.nio.cs.StreamEncoder.writeBytes(StreamEncoder.java:233)
	at java.base/sun.nio.cs.StreamEncoder.implFlushBuffer(StreamEncoder.java:312)
	at java.base/sun.nio.cs.StreamEncoder.flushBuffer(StreamEncoder.java:104)
	at java.base/java.io.OutputStreamWriter.flushBuffer(OutputStreamWriter.java:184)
	at java.base/java.io.PrintStream.write(PrintStream.java:606)
	at java.base/java.io.PrintStream.print(PrintStream.java:745)
	at java.base/java.io.PrintStream.println(PrintStream.java:882)
	at FoxHoundUI.positionQuery(FoxHoundUI.java:264)
	at FoxHoundUIAdvancedTest.checkPosQueryOutput(FoxHoundUIAdvancedTest.java:284)
	at FoxHoundUIAdvancedTest.testPositionQueryLargeBoardMessage(FoxHoundUIAdvancedTest.java:388)
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
27) testDisplayBoardLeadingZero(FoxHoundUIAdvancedTest): Error executing displayBoard  for console output check:java.lang.IllegalArgumentException: ERROR: A players coordinate is not on the board
java.lang.AssertionError: Error executing displayBoard  for console output check:java.lang.IllegalArgumentException: ERROR: A players coordinate is not on the board
	at org.junit.Assert.fail(Assert.java:88)
	at FoxHoundUIAdvancedTest.checkDisplay(FoxHoundUIAdvancedTest.java:118)
	at FoxHoundUIAdvancedTest.testDisplayBoardLeadingZero(FoxHoundUIAdvancedTest.java:189)
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

Tests run: 87, Failures: 27
0b01e967-ec68-4239-8aa2-f24b021e0d0e
END 0.6896552

```

Errors:
```
ERROR: java.io.FileNotFoundException: testFolder (Is a directory)
```

