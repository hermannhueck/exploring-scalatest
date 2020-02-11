package _02declaringtests

class Ex07FailingSuite extends munit.FunSuite {

  // this test is expected to fail,
  // i.e. it passes when it fails.
  test("issue-456".fail) {
    // Reproduce reported bug
    assert(42 == 43)
  }
}
