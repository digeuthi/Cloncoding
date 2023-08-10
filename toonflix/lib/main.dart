import 'package:flutter/material.dart';
import 'package:toonflix/widgets/Button.dart';

// class Player {
//   String? name; // ?를 한 순간부터 name을 가질수도 안가질수도 있다
//   required가 아니라는 이야기.
//   Player(); // ?만으로 안에 굳이 값을 가지지 않아도 오류가 뜨지 않는다.
//   Player({required this.name}); //중괄호 안에 표시하면 named Constructor
//   Player(this.name); 이렇게 하는게 positional 방법 -> 위치를 정확히 해줘야함
// }

void main() {
  //var nico = Player();
  runApp(const App());
}

//widget = 레고블록 = class! 매번 new를 통해서 초기화 시켜주지 않아도 된다.
//widget을 쌓아서 하나의 UI를 만들게 된다.

//meterial , cupertino : 구글, 애플 디자인 시스템
//scaffold 앱을 구성할때 팰요한 요소
//class만들고 나서 ,찍어주면 어떤 요소를 하고 있는지 알수 있다!

//constant는 바꿀수 없는 상수, compile 전에 알고 있는 값이다.
//하지만 어떤것이 constant가 될수 있고 없고를 판단하기 어렵다
//open user Settings (Json)에서 처리 가능
// "editor.codeActionsOnSave": {
//         "source.fixAll": true
//     },
//     "dart.previewFlutterUiGuides": true, 이 부분 추가.

//show code action : ctrl + .

class App extends StatelessWidget {
  const App({super.key});

  //Root Widget이 된다.
  //StatelessWidget을 사용하면 build 메서드를 구현해야한다
  @override
  Widget build(BuildContext context) {
    //App의 시작점을 정해주는것
    return MaterialApp(
      home: Scaffold(
          backgroundColor: const Color(0xff181818), //Colors.하면 지정된 색들을 사용
          body: Padding(
            //전체적인 코드를 padding으로 감싸주는것.
            padding: const EdgeInsets.symmetric(
              horizontal: 40,
            ),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              //Row는 수평, Column은 수직배열을 위한것.
              //Row에서는 가로가 main, Column에서는 세로가 main
              children: [
                const SizedBox(
                  //사이즈를 가진 빈 박스, 이걸 사용해서 공간을 만든다.
                  height: 80,
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.end,
                  children: [
                    Column(
                      crossAxisAlignment: CrossAxisAlignment.end,
                      //수평방향정렬
                      children: [
                        const Text(
                          "Hey, Selena",
                          style: TextStyle(
                            color: Colors.white,
                            fontSize: 28,
                            fontWeight: FontWeight.w800,
                          ),
                        ),
                        Text(
                          "Welcome back",
                          style: TextStyle(
                            color: Colors.white.withOpacity(0.8),
                            fontSize: 18,
                          ),
                        ),
                      ],
                    )
                  ],
                ),
                const SizedBox(
                  height: 50,
                ),
                Text(
                  'Total Balance',
                  style: TextStyle(
                    fontSize: 22,
                    color: Colors.white.withOpacity(0.8),
                  ),
                ),
                const SizedBox(
                  height: 5,
                ),
                const Text(
                  '\$5 194 482',
                  style: TextStyle(
                    fontSize: 48,
                    fontWeight: FontWeight.w600,
                    color: Colors.white,
                  ),
                ),
                const SizedBox(
                  height: 30,
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: const [
                    Button(
                      text: "Transfer",
                      bgColor: Color(0xFFF1B33B),
                      textColor: Colors.black,
                    ),
                    Button(
                      text: "Request",
                      bgColor: Color(0xFF1F2123),
                      textColor: Colors.white,
                    ),
                  ],
                ),
                const SizedBox(
                  height: 100,
                ),
                Row(
                  crossAxisAlignment: CrossAxisAlignment
                      .end, //Row에서는 main이 가로, cross가 세로 , Column에서는 main이 세로 cross가 가로
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    const Text(
                      'Wallets',
                      style: TextStyle(
                        color: Colors.white,
                        fontSize: 38,
                        fontWeight: FontWeight.w600,
                      ),
                    ),
                    Text(
                      'View All',
                      style: TextStyle(
                        color: Colors.white.withOpacity(0.8),
                        fontSize: 18,
                      ),
                    ),
                  ],
                ),
                const SizedBox(
                  height: 20,
                ),
                Container(
                  clipBehavior:
                      Clip.hardEdge, //Container에서 튀어나온 부분을 어떻게 처리할 것인지를 정하게된다.
                  decoration: BoxDecoration(
                    color: const Color(0xFF1F2123),
                    borderRadius: BorderRadius.circular(20),
                  ),
                  child: Padding(
                    padding: const EdgeInsets.all(30),
                    child: Row(
                      mainAxisAlignment: MainAxisAlignment
                          .spaceBetween, //Row main 가로 Column main 세로
                      children: [
                        Column(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: [
                            const Text(
                              "Euro",
                              style: TextStyle(
                                color: Colors.white,
                                fontSize: 32,
                                fontWeight: FontWeight.w600,
                              ),
                            ),
                            const SizedBox(
                              height: 10,
                            ),
                            Row(
                              children: [
                                const Text(
                                  "6 428",
                                  style: TextStyle(
                                    color: Colors.white,
                                    fontSize: 20,
                                  ),
                                ),
                                const SizedBox(
                                  width: 5,
                                ),
                                Text(
                                  "EUR",
                                  style: TextStyle(
                                    color: Colors.white.withOpacity(0.8),
                                    fontSize: 20,
                                  ),
                                ),
                              ],
                            ),
                          ],
                        ),
                        Transform.scale(
                          //카드의 크기는 변하게 하지 않고 아이콘의 크기만을 변화시키게 된다.
                          scale: 2.2,
                          child: Transform.translate(
                            offset:
                                const Offset(-5, 13), // x, y축으로의 이동하는걸로 사용하는 위젯
                            child: const Icon(
                              Icons.euro_rounded,
                              color: Colors.white,
                              size: 88,
                            ),
                          ),
                        ),
                      ],
                    ),
                  ),
                ),
              ],
            ),
          )),
    );
  }
}
