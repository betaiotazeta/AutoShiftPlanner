<?xml version="1.0" encoding="UTF-8"?>

<plannerBenchmark>
    <parallelBenchmarkCount>AUTO</parallelBenchmarkCount>
    <benchmarkDirectory>local/benchmarkReport/</benchmarkDirectory>
    <inheritedSolverBenchmark>
        <problemBenchmarks>
            <inputSolutionFile>data/unsolved/asp_7employees_forbidden_mandatory.xml</inputSolutionFile>
            <!-- <inputSolutionFile>local/solutionFile/anotherSolutionFile.xml</inputSolutionFile> -->
        </problemBenchmarks>
        <solver>
            <!-- Common solver configuration -->
            <environmentMode>NON_REPRODUCIBLE</environmentMode>

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
                <secondsSpentLimit>300</secondsSpentLimit>
            </termination>
            
            <constructionHeuristic>
                <constructionHeuristicType>FIRST_FIT</constructionHeuristicType>
            </constructionHeuristic>
                        
        </solver>
        <subSingleCount>1</subSingleCount>
    </inheritedSolverBenchmark>


    <#list [0, 3, 5, 7, 10, 12, 14] as entityTabuSize>
    <#list [50, 100] as acceptedCountLimit>
    <solverBenchmark>
        <name>Tabu Search entityTabuSize ${entityTabuSize} acceptedCountLimit ${acceptedCountLimit}</name>
        <solver>
            
            <!-- First localSearch phase -->
            <localSearch>
                <unionMoveSelector>          
                    <changeMoveSelector/>
                    <swapMoveSelector/>          
                    <pillarChangeMoveSelector>
                        <pillarSelector>
                            <subPillarEnabled>true</subPillarEnabled>
                            <minimumSubPillarSize>2</minimumSubPillarSize>
                            <maximumSubPillarSize>4</maximumSubPillarSize>
                        </pillarSelector>
                    </pillarChangeMoveSelector>          
                    <pillarSwapMoveSelector>
                        <pillarSelector>
                            <subPillarEnabled>true</subPillarEnabled>
                            <minimumSubPillarSize>2</minimumSubPillarSize>
                            <maximumSubPillarSize>4</maximumSubPillarSize>
                        </pillarSelector>
                    </pillarSwapMoveSelector>
                </unionMoveSelector>
                
                <acceptor>
                    <entityTabuSize>${entityTabuSize}</entityTabuSize>
                </acceptor>
                
                <forager>
                    <pickEarlyType>FIRST_BEST_SCORE_IMPROVING</pickEarlyType>
                    <acceptedCountLimit>${acceptedCountLimit}</acceptedCountLimit>
                </forager>
                
                <termination>
                    <secondsSpentLimit>60</secondsSpentLimit>
                </termination>
            </localSearch>
            
            <!-- Second localSearch phase -->
            <localSearch>
                <unionMoveSelector>        
                    <changeMoveSelector/>
                    <swapMoveSelector/>          
                    <pillarChangeMoveSelector>
                        <pillarSelector>
                            <subPillarEnabled>true</subPillarEnabled>
                            <minimumSubPillarSize>2</minimumSubPillarSize>
                            <maximumSubPillarSize>4</maximumSubPillarSize>
                        </pillarSelector>
                    </pillarChangeMoveSelector>          
                    <pillarSwapMoveSelector>
                        <pillarSelector>
                            <subPillarEnabled>true</subPillarEnabled>
                            <minimumSubPillarSize>2</minimumSubPillarSize>
                            <maximumSubPillarSize>4</maximumSubPillarSize>
                        </pillarSelector>
                    </pillarSwapMoveSelector>
                </unionMoveSelector>
 
                <acceptor>
                    <entityTabuSize>5</entityTabuSize>
                </acceptor>

                <forager>
                    <acceptedCountLimit>1000</acceptedCountLimit>
                </forager>
            </localSearch>
            
        </solver>
    </solverBenchmark>
    </#list>
    </#list>
                                                                                                                                                                              
</plannerBenchmark>