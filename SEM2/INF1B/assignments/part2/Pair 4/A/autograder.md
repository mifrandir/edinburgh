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

e0b4f917-e9b4-4970-aac6-c945cd288690 START
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
Failed to load from file: java.io.FileNotFoundException: path/to/unlikely/file.txt (No such file or directory)
Running test: testLoadGameInvalidFile [PASSED]
Running test: testLoadGameValidInput [PASSED]
Running test: testSaveGamePathNull [PASSED]
Running test: testLoadGamePlayersNonDefaultDim [PASSED]
Invalid file. Invalid turn field length.
Invalid file.
Invalid file.
Invalid file.
Invalid file. Invalid turn char.
Invalid file. Player off board.
Running test: testLoadGameInvalidFileContent [PASSED]
Running test: testSaveGamePlayersNonDefaultDim [PASSED]
Failed to write to file: java.io.FileNotFoundException: path/to/a/file/that/does/not/exist/game.txt (No such file or directory)
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

Time: 0.176

 (28 tests)
e0b4f917-e9b4-4970-aac6-c945cd288690
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
Score: 32.643678 / 40.0
Output:
```
Running tests for [class FoxHoundUtilsAdvancedTest, class FoxHoundIOAdvancedTest, class FoxHoundUIAdvancedTest]

36d2daa1-a892-4a82-a3e2-3831cd504f01 START
Running test: testInitialisePositionsTooSmallDim [PASSED]
Running test: testIsHoundWinPositiveCornerBlockBottomLeft [PASSED]
Running test: testIsValidMoveDestInvalidBoardCoord1 [PASSED]
Running test: testIsValidMoveDestInvalidBoardCoord2 [PASSED]
Running test: testIsValidMoveDestInvalidBoardCoord3 [PASSED]
Running test: testIsHoundWinInvalidDimension1 [PASSED]
Running test: testIsHoundWinInvalidDimension2 [PASSED]
Running test: testIsHoundWinInvalidDimension3 [PASSED]
Running test: testInitialisePositionsMinField [PASSED]
Running test: testIsValidMoveInvalidBoardSetup [FAILED]
Running test: testIsValidMoveDestTooFar [PASSED]
Running test: testIsValidMoveDestNull [PASSED]
Running test: testIsValidMovePlayersInvalidBoardCoord1 [PASSED]
Running test: testIsValidMovePlayersInvalidBoardCoord2 [PASSED]
Running test: testIsValidMovePlayersInvalidBoardCoord3 [PASSED]
Running test: testIsValidMovePlayersInvalidBoardCoord4 [PASSED]
Running test: testIsValidMovePositiveNonDefaultDimension [PASSED]
Running test: testIsValidMoveInvalidFigure [PASSED]
Running test: testInitialisePositionsEvenDimOddHalf [FAILED]
Running test: testIsValidMoveHoundBackwards [PASSED]
Running test: testIsHoundWinInvalidBoardSetup [FAILED]
Running test: testInitialisePositionsOddDimEvenHalf [FAILED]
Running test: testIsValidMoveSameOriginAndDest [PASSED]
Running test: testIsHoundWinPositiveWallBlockRight [PASSED]
Running test: testInitialisePositionsZeroDim [PASSED]
Running test: testInitialisePositionsMaxField [FAILED]
Running test: testIsValidMoveOriginNull [PASSED]
Running test: testIsValidMovePlayersNull [PASSED]
Running test: testInitialisePositionsOddDimOddHalf [PASSED]
Running test: testIsFoxWinInvalidBoardCoord1 [PASSED]
Running test: testIsFoxWinInvalidBoardCoord2 [PASSED]
Running test: testIsFoxWinInvalidBoardCoord3 [PASSED]
Running test: testIsHoundWinInvalidBoardCoord1 [PASSED]
Running test: testIsHoundWinInvalidBoardCoord2 [PASSED]
Running test: testIsHoundWinInvalidBoardCoord3 [PASSED]
Running test: testIsHoundWinInvalidBoardCoord4 [PASSED]
Running test: testIsHoundWinPositiveWallBlockLeft [PASSED]
Running test: testInitialisePositionsEvenDimEvenHalf [PASSED]
Running test: testInitialisePositionsTooLargeDim [PASSED]
Running test: testIsFoxWinPositiveNonDefaultDim [PASSED]
Running test: testIsValidMoveOriginInvalidBoardCoord1 [PASSED]
Running test: testIsValidMoveOriginInvalidBoardCoord2 [PASSED]
Running test: testIsValidMoveOriginInvalidBoardCoord3 [PASSED]
Running test: testIsHoundWinPositiveNonDefaultDim [PASSED]
Running test: testIsFoxWinNegativeNonDefaultDim [PASSED]
Running test: testIsValidMoveInvalidDimension1 [PASSED]
Running test: testIsValidMoveInvalidDimension2 [PASSED]
Running test: testIsValidMoveInvalidDimension3 [PASSED]
Running test: testIsValidMoveInvalidDimension4 [PASSED]
Running test: testIsHoundWinPositiveWallBlockBottom [PASSED]
Running test: testIsHoundWinNegativeNonDefaultDim [PASSED]
Failed to load from file: java.io.FileNotFoundException: data01.txt (No such file or directory)
Running test: testLoadGameInvalidBoardCoord1 [FAILED]
Failed to load from file: java.io.FileNotFoundException: data01.txt (No such file or directory)
Running test: testLoadGameInvalidBoardCoord2 [FAILED]
Failed to load from file: java.io.FileNotFoundException: data01.txt (No such file or directory)
Running test: testLoadGameInvalidBoardCoord3 [FAILED]
Failed to load from file: java.io.FileNotFoundException: data01.txt (No such file or directory)
Running test: testLoadGameInvalidBoardCoord4 [FAILED]
Running test: testLoadGamePlayersNull [PASSED]
Running test: testSaveGameInvalidMove [PASSED]
Failed to load from file: java.io.FileNotFoundException: testFolder (Is a directory)
Running test: testLoadGameFileIsFolder [PASSED]
Running test: testSaveGamePlayersNull [PASSED]
Running test: testSaveGameInvalidBoardCoord1 [PASSED]
Running test: testSaveGameInvalidBoardCoord2 [FAILED]
Running test: testSaveGameInvalidBoardCoord3 [FAILED]
Running test: testSaveGameInvalidBoardCoord4 [FAILED]
Failed to save. File already exists.
Running test: testSaveGamePathExists [PASSED]
Running test: testPositionQueryScannerNull [PASSED]
Running test: testPositionQueryInvalidDimension1 [PASSED]
Running test: testPositionQueryInvalidDimension2 [PASSED]
Running test: testPositionQueryInvalidDimension3 [PASSED]
Running test: testPositionQueryInvalidDimension4 [PASSED]
Running test: testDisplayBoardLargeDim [PASSED]
Running test: testPositionQuerySmallBoardMessage [FAILED]
Running test: testDisplayBoardInvalidDimension1 [PASSED]
Running test: testDisplayBoardInvalidDimension2 [PASSED]
Running test: testDisplayBoardInvalidDimension3 [PASSED]
Running test: testDisplayBoardInvalidDimension4 [PASSED]
Running test: testDisplayBoardSmallDim [PASSED]
Running test: testPositionQueryInvalidInput1 [PASSED]
Running test: testPositionQueryInvalidInput2 [FAILED]
Running test: testPositionQueryLargeBoardMessage [FAILED]
Running test: testDisplayBoardPlayersNull [PASSED]
Running test: testDisplayBoardPlayersInvalidBoardCoord1 [PASSED]
Running test: testDisplayBoardPlayersInvalidBoardCoord2 [PASSED]
Running test: testDisplayBoardPlayersInvalidBoardCoord3 [PASSED]
Running test: testDisplayBoardPlayersInvalidBoardCoord4 [PASSED]
Running test: testDisplayBoardInvalidBoardSetup [FAILED]
Running test: testDisplayBoardLeadingZero [PASSED]
Running test: testFileQueryScannerNull [PASSED]

Time: 0.362
16 failure(s)
1) testIsValidMoveInvalidBoardSetup(FoxHoundUtilsAdvancedTest): Expected exception: java.lang.IllegalArgumentException
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
2) testInitialisePositionsEvenDimOddHalf(FoxHoundUtilsAdvancedTest): Returned positions not as expected for dimension 6: arrays first differed at element [3]; expected:<[C]6> but was:<[D]6>
Returned positions not as expected for dimension 6: arrays first differed at element [3]; expected:<[C]6> but was:<[D]6>
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
Caused by: org.junit.ComparisonFailure: expected:<[C]6> but was:<[D]6>
	at org.junit.Assert.assertEquals(Assert.java:115)
	at org.junit.Assert.assertEquals(Assert.java:144)
	at org.junit.internal.ExactComparisonCriteria.assertElementsEqual(ExactComparisonCriteria.java:8)
	at org.junit.internal.ComparisonCriteria.arrayEquals(ComparisonCriteria.java:53)
	... 35 more
3) testIsHoundWinInvalidBoardSetup(FoxHoundUtilsAdvancedTest): Expected exception: java.lang.IllegalArgumentException
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
4) testInitialisePositionsOddDimEvenHalf(FoxHoundUtilsAdvancedTest): Returned positions not as expected for dimension 5: arrays first differed at element [2]; expected:<[D]5> but was:<[C]5>
Returned positions not as expected for dimension 5: arrays first differed at element [2]; expected:<[D]5> but was:<[C]5>
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
Caused by: org.junit.ComparisonFailure: expected:<[D]5> but was:<[C]5>
	at org.junit.Assert.assertEquals(Assert.java:115)
	at org.junit.Assert.assertEquals(Assert.java:144)
	at org.junit.internal.ExactComparisonCriteria.assertElementsEqual(ExactComparisonCriteria.java:8)
	at org.junit.internal.ComparisonCriteria.arrayEquals(ComparisonCriteria.java:53)
	... 35 more
5) testInitialisePositionsMaxField(FoxHoundUtilsAdvancedTest): Returned positions not as expected for dimension 26: arrays first differed at element [13]; expected:<[M]26> but was:<[N]26>
Returned positions not as expected for dimension 26: arrays first differed at element [13]; expected:<[M]26> but was:<[N]26>
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
Caused by: org.junit.ComparisonFailure: expected:<[M]26> but was:<[N]26>
	at org.junit.Assert.assertEquals(Assert.java:115)
	at org.junit.Assert.assertEquals(Assert.java:144)
	at org.junit.internal.ExactComparisonCriteria.assertElementsEqual(ExactComparisonCriteria.java:8)
	at org.junit.internal.ComparisonCriteria.arrayEquals(ComparisonCriteria.java:53)
	... 35 more
6) testLoadGameInvalidBoardCoord1(FoxHoundIOAdvancedTest): Expected exception: java.lang.NullPointerException
java.lang.AssertionError: Expected exception: java.lang.NullPointerException
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
7) testLoadGameInvalidBoardCoord2(FoxHoundIOAdvancedTest): Expected exception: java.lang.IllegalArgumentException
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
8) testLoadGameInvalidBoardCoord3(FoxHoundIOAdvancedTest): Expected exception: java.lang.IllegalArgumentException
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
9) testLoadGameInvalidBoardCoord4(FoxHoundIOAdvancedTest): Expected exception: java.lang.IllegalArgumentException
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
10) testSaveGameInvalidBoardCoord2(FoxHoundIOAdvancedTest): Unexpected exception, expected<java.lang.IllegalArgumentException> but was<java.lang.NullPointerException>
java.lang.Exception: Unexpected exception, expected<java.lang.IllegalArgumentException> but was<java.lang.NullPointerException>
	at org.junit.internal.runners.statements.ExpectException.evaluate(ExpectException.java:28)
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
Caused by: java.lang.NullPointerException
	at FoxHoundUtils.tileIsOnBoard(FoxHoundUtils.java:137)
	at FoxHoundIO.saveGame(FoxHoundIO.java:21)
	at FoxHoundIOAdvancedTest.testSaveGameInvalidBoardCoord2(FoxHoundIOAdvancedTest.java:126)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:566)
	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
	at org.junit.internal.runners.statements.ExpectException.evaluate(ExpectException.java:19)
	... 23 more
11) testSaveGameInvalidBoardCoord3(FoxHoundIOAdvancedTest): Unexpected exception, expected<java.lang.IllegalArgumentException> but was<java.lang.NullPointerException>
java.lang.Exception: Unexpected exception, expected<java.lang.IllegalArgumentException> but was<java.lang.NullPointerException>
	at org.junit.internal.runners.statements.ExpectException.evaluate(ExpectException.java:28)
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
Caused by: java.lang.NullPointerException
	at FoxHoundUtils.tileIsOnBoard(FoxHoundUtils.java:137)
	at FoxHoundIO.saveGame(FoxHoundIO.java:21)
	at FoxHoundIOAdvancedTest.testSaveGameInvalidBoardCoord3(FoxHoundIOAdvancedTest.java:136)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:566)
	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
	at org.junit.internal.runners.statements.ExpectException.evaluate(ExpectException.java:19)
	... 23 more
12) testSaveGameInvalidBoardCoord4(FoxHoundIOAdvancedTest): Unexpected exception, expected<java.lang.IllegalArgumentException> but was<java.lang.NullPointerException>
java.lang.Exception: Unexpected exception, expected<java.lang.IllegalArgumentException> but was<java.lang.NullPointerException>
	at org.junit.internal.runners.statements.ExpectException.evaluate(ExpectException.java:28)
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
Caused by: java.lang.NullPointerException
	at FoxHoundUtils.tileIsOnBoard(FoxHoundUtils.java:137)
	at FoxHoundIO.saveGame(FoxHoundIO.java:21)
	at FoxHoundIOAdvancedTest.testSaveGameInvalidBoardCoord4(FoxHoundIOAdvancedTest.java:146)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:566)
	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
	at org.junit.internal.runners.statements.ExpectException.evaluate(ExpectException.java:19)
	... 23 more
13) testPositionQuerySmallBoardMessage(FoxHoundUIAdvancedTest): Console output stdout not as expected in positionQuery: B1 A2
 expected:<...ositions between A1-[D4]:> but was:<...ositions between A1-[H8]:>
org.junit.ComparisonFailure: Console output stdout not as expected in positionQuery: B1 A2
 expected:<...ositions between A1-[D4]:> but was:<...ositions between A1-[H8]:>
	at org.junit.Assert.assertEquals(Assert.java:115)
	at FoxHoundUIAdvancedTest.checkPosQueryOutput(FoxHoundUIAdvancedTest.java:291)
	at FoxHoundUIAdvancedTest.testPositionQuerySmallBoardMessage(FoxHoundUIAdvancedTest.java:399)
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
14) testPositionQueryInvalidInput2(FoxHoundUIAdvancedTest): Console output stdout not as expected in positionQuery: E1 F9
E8 F7
 expected:<...ions between A1-H8:
[
]Provide origin and d...> but was:<...ions between A1-H8:
[]Provide origin and d...>
org.junit.ComparisonFailure: Console output stdout not as expected in positionQuery: E1 F9
E8 F7
 expected:<...ions between A1-H8:
[
]Provide origin and d...> but was:<...ions between A1-H8:
[]Provide origin and d...>
	at org.junit.Assert.assertEquals(Assert.java:115)
	at FoxHoundUIAdvancedTest.checkPosQueryOutput(FoxHoundUIAdvancedTest.java:291)
	at FoxHoundUIAdvancedTest.testPositionQueryInvalidInput2(FoxHoundUIAdvancedTest.java:329)
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
15) testPositionQueryLargeBoardMessage(FoxHoundUIAdvancedTest): Console output stdout not as expected in positionQuery: F1 Z25
 expected:<...ositions between A1-[Z26]:> but was:<...ositions between A1-[H8]:>
org.junit.ComparisonFailure: Console output stdout not as expected in positionQuery: F1 Z25
 expected:<...ositions between A1-[Z26]:> but was:<...ositions between A1-[H8]:>
	at org.junit.Assert.assertEquals(Assert.java:115)
	at FoxHoundUIAdvancedTest.checkPosQueryOutput(FoxHoundUIAdvancedTest.java:291)
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
16) testDisplayBoardInvalidBoardSetup(FoxHoundUIAdvancedTest): Unexpected exception, expected<java.lang.IllegalArgumentException> but was<java.lang.ArrayIndexOutOfBoundsException>
java.lang.Exception: Unexpected exception, expected<java.lang.IllegalArgumentException> but was<java.lang.ArrayIndexOutOfBoundsException>
	at org.junit.internal.runners.statements.ExpectException.evaluate(ExpectException.java:28)
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
Caused by: java.lang.ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5
	at FoxHoundUI.displayBoard(FoxHoundUI.java:98)
	at FoxHoundUIAdvancedTest.testDisplayBoardInvalidBoardSetup(FoxHoundUIAdvancedTest.java:241)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:566)
	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
	at org.junit.internal.runners.statements.ExpectException.evaluate(ExpectException.java:19)
	... 23 more

Tests run: 87, Failures: 16
36d2daa1-a892-4a82-a3e2-3831cd504f01
END 0.81609195

```

Errors:
```

```

