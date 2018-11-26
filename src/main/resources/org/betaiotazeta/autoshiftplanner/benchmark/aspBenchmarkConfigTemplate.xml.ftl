<?xml version="1.0" encoding="UTF-8"?>

<plannerBenchmark>
    <parallelBenchmarkCount>AUTO</parallelBenchmarkCount>
    <benchmarkDirectory>local/benchmarkReport/</benchmarkDirectory>
    <inheritedSolverBenchmark>
        <problemBenchmarks>
            <xStreamAnnotatedClass>org.betaiotazeta.autoshiftplanner.Solution</xStreamAnnotatedClass>
            <inputSolutionFile>data/unsolved/asp_7employees_forbidden_mandatory.xml</inputSolutionFile>
            <!-- <inputSolutionFile>local/solutionFile/anotherSolutionFile.xml</inputSolutionFile> -->
        </problemBenchmarks>
        <solver>
            <!-- Common solver configuration -->
            <environmentMode>REPRODUCIBLE</environmentMode>

            <!-- Domain model configuration -->
            <scanAnnotatedClasses>
                <packageInclude>org.betaiotazeta.autoshiftplanner</packageInclude>
            </scanAnnotatedClasses>

            <!-- Score configuration -->
            <scoreDirectorFactory>
                <easyScoreCalculatorClass>org.betaiotazeta.autoshiftplanner.AspEasyScoreCalculator</easyScoreCalculatorClass>
                <initializingScoreTrend>ANY</initializingScoreTrend>
            </scoreDirectorFactory>
            
            <termination>
                <bestScoreLimit>0hard/0soft</bestScoreLimit>
                <secondsSpentLimit>180</secondsSpentLimit>
            </termination>
            
            <constructionHeuristic>
                <constructionHeuristicType>FIRST_FIT</constructionHeuristicType>
            </constructionHeuristic>
                        
        </solver>
        <subSingleCount>1</subSingleCount>
    </inheritedSolverBenchmark>


    <#list [100, 200, 400, 600, 800] as stepCountingHillClimbingSize>
    <#list [1, 2, 4, 6, 8] as acceptedCountLimit>
    <solverBenchmark>
        <name>Step-Counting-HC stepCountingHillClimbingSize ${stepCountingHillClimbingSize} acceptedCountLimit ${acceptedCountLimit}</name>
        <solver>
            
            <!-- localSearch phase -->
            <localSearch>

                <acceptor>
                    <stepCountingHillClimbingSize>400</stepCountingHillClimbingSize>
                </acceptor>
                <forager>
                    <acceptedCountLimit>1</acceptedCountLimit>
                </forager>

            </localSearch>
            
        </solver>
    </solverBenchmark>
    </#list>
    </#list>
                                                                                                                                                                              
</plannerBenchmark>