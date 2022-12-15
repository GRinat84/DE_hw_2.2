import scala.io.StdIn.readLine

object App {
  private def calc_payment(salary : Double, bonus_prc : Double, meal : Double, tax_prc : Double): Double = {
    return ((salary * (1 + (bonus_prc / 100))) + meal) * (1 - tax_prc / 100)
  }

  private def show_salaries(salaries: List[Double]) = {
    for (salary_element <- salaries) {
      print("%f ".format(salary_element))
    }
    println("")
  }

  private def addElementToListByIndex[T](list: List[T], i: Int, value: T): List[T] = {
    val (front, back) = list.splitAt(i)
    return front ++ List(value) ++ back
  }

  def main(args: Array[String]): Unit = {
    val hello_world = "Hello, World!"

    // 3.a.i:  выводит фразу «Hello, Scala!» справа налево
    println(hello_world.reverse)

    // 3.a.ii: переводит всю фразу в нижний регистр
    println(hello_world.toLowerCase)

    // 3.a.iii: удаляет символ!
    println(hello_world.replace("!", ""))

    // 3.a.iv: добавляет в конец фразы «and goodbye python!»
    println(hello_world.concat(" and goodbye python!"))

    // 3.b: Напишите программу, которая вычисляет ежемесячный оклад сотрудника после вычета налогов.
    // На вход вашей программе подается значение годового дохода до вычета налогов, размер премии – в процентах от годового дохода и компенсация питания.

    print("Введите годовой оклад: ")
    val salary_year =  readLine().toDouble
    print("Введите процент бонуса: ")
    val bonus_prc =  readLine().toDouble
    print("Месячная компенсация питания: ")
    val meal_compensation_month = readLine().toDouble
    val tax_prc = 13

    var salary_month = salary_year / 12
    var payment_month = calc_payment(salary_month, bonus_prc, meal_compensation_month, tax_prc)

    println("Оклад: %f, годовой бонус: %f%%, коменсация питания (мес): %f".format(salary_year, bonus_prc, meal_compensation_month))

    println("Месячная ЗП к выплате: %f".format(payment_month))


    // 3.c:  Напишите программу, которая рассчитывает для каждого сотрудника отклонение(в процентах) от среднего значения оклада на уровень всего отдела.
    // В итоговом значении должно учитываться в большую или меньшую сторону отклоняется размер оклада.
    // На вход вышей программе подаются все значения, аналогичные предыдущей программе, а также список со значениями окладов сотрудников отдела 100, 150, 200, 80, 120, 75.

    var salaries = List(100.0, 150.0, 200.0, 80.0, 120.0, 75.0)
    val department_salary_avg = salaries.sum / salaries.size.toFloat

    println("Средняя заработная плата в отделе: %f".format(department_salary_avg.toFloat))

    print("Отклонения заработных плат: ")
    for(salary_element <- salaries){
      print("%f%% ".format((salary_element.toFloat - department_salary_avg) * 100.0 / department_salary_avg))
    }
    println("")

    // 3.d: Попробуйте рассчитать новую зарплату сотрудника, добавив(или отняв, если сотрудник плохо себя вел) необходимую сумму с учетом результатов прошлого задания.
    // Добавьте его зарплату в список и вычислите значение самой высокой зарплаты и самой низкой.

    salary_month = salary_month * 1.15
    salaries = salary_month :: salaries
    show_salaries(salaries)

    println("Максимальный оклад: %f, минимальный: %f".format(salaries.max, salaries.min))

    // 3.e: Также в вашу команду пришли два специалиста с окладами 350 и 90 тысяч рублей. Попробуйте отсортировать список сотрудников по уровню оклада от меньшего к большему.

    salaries = 350.0 :: 90.0 :: salaries
    salaries = salaries.sorted
    show_salaries(salaries)

    // 3.f: Кажется, вы взяли в вашу команду еще одного сотрудника и предложили ему оклад 130 тысяч.
    // Вычислите самостоятельно номер сотрудника в списке так, чтобы сортировка не нарушилась и добавьте его на это место.

    val new_salary = 130.0
    println("Добавлеяем нового сотрудника с окладом %f".format(new_salary))
    salaries = addElementToListByIndex(salaries, salaries.indexOf(salaries.find(_ > new_salary).min), new_salary)
    show_salaries(salaries)

    // 3.g: Попробуйте вывести номера сотрудников из полученного списка, которые попадают под категорию middle.
    // На входе программе подается «вилка» зарплаты специалистов уровня middle.

    print("Нижняя планка middle: ")
    val middle_low =  readLine().toDouble
    print("Верхняя планка middle: ")
    val middle_high = readLine().toDouble

    print("Оклады middle'ов: ")
    for (salary_element <- salaries) {
      if (salary_element >= middle_low && salary_element <= middle_high)
        print("%f ".format(salary_element.toFloat))
    }

    // 3.h: Однако наступил кризис и ваши сотрудники требуют повысить зарплату. Вам необходимо проиндексировать зарплату каждого сотрудника на уровень инфляции – 7%

    salaries.zip(0 until salaries.size).foreach {
        case (elem, idx) => salaries = salaries.patch(idx, Seq(salaries(idx) * 1.07), 1)
    }
    println("Проиндексированные (на 7%) оклады: ")
    show_salaries(salaries)

  }
}
