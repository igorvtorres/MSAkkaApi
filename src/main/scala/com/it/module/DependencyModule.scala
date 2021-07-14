package com.it.module
import com.google.inject.{AbstractModule, PrivateModule}
import net.codingwell.scalaguice.{ScalaModule, ScalaPrivateModule}

//https://github.com/codingwell/scala-guice
class DependencyModule extends AbstractModule with ScalaModule{

  override def configure(): Unit = {
    //bind[Service].to[ServiceImpl].in[Singleton]
    //bind[CreditCardPaymentService]
    //bind[Bar[Foo]].to[FooBarImpl]
    //bind[PaymentService].annotatedWith(Names.named("paypal")).to[CreditCardPaymentService]
  }
}

