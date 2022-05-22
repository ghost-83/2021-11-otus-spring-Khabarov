insert into genre (id, `name`)
values (1, 'python'),
       (2, 'linux'),
       (3, 'java'),
       (4, 'kotlin'),
       (5, 'react'),
       (6, 'arduino'),
       (7, 'postgres'),
       (8, 'spring'),
       (9, 'raspberry'),
       (10, 'vs_code'),
       (11, 'nginx'),
       (12, 'docker'),
       (13, 'android'),
       (14, 'c'),
       (15, 'cpp'),
       (16, 'ssh'),
       (17, 'js'),
       (18, 'css'),
       (19, 'json'),
       (20, 'typescript');

insert into author (id, first_name, last_name)
values (1, 'Иванов', 'Иван'),
       (2, 'Петров', 'Петр');

insert into post (id, title, text, image, author_id, genre_id)
values ( 1, 'Как перенести / переместить образ Docker в другую систему?',
         '<p class="gp">
             В идеальном случае передача изображений Docker осуществляется через реестр Docker или через полностью управляемого поставщика, такого как AWS ECR или Google GCR. Вы можете легко загрузить изображение с помощью команды docker push , а другие могут получить изображение с помощью команды docker pull .
         </p>
         <p class="gp">
             Хотя, если вам нужно переместить изображение с одного хоста на другой, чтобы протестировать его перед отправкой в производственную среду, или вы хотите поделиться этим изображением с кем-то в офисе, это можно сделать, экспортировав изображение как .tar файл.
         </p>
         <p class="gp">
             Docker поддерживает два разных типа методов для сохранения изображений контейнера в один архив.
         </p>
         <ui>
             <li>Docker save - Save используется для сохранения изображения (не контейнера)</li>
             <li>Docker export - Export используется для сохранения контейнера (не изображения)</li>
         </ui>
         <h1 class="gh">
             Использование Docker Save Command:
         </h1>
         <h3 class="gh">
             Сохранение изображения Docker:
         </h3>
         <p class="gp">
             Во-первых, мы будем придерживаться плана, который сохраняет только изображение. Теперь пройдемся по команде docker save . Предположим, вам нужен образ Python с Alpine, который можно извлечь из Docker Hub:
         </p>
         <pre>
             <code class="sh">
                 $ docker pull python:2.7.17-alpine3.9
                 2.7.17-alpine3.9: Pulling from library/python
                 e7c96db7181b: Already exists
                 1819f4b92bc2: Already exists
                 8061b3761cb3: Pull complete
                 73aebae115de: Pull complete
                 Digest: sha256:5f6059d78f530c3c59c4842e104ddcfc772a27fb8fac0d900f4d77bcb4621d9b
                 Status: Downloaded newer image for python:2.7.17-alpine3.9
                 docker.io/library/python:2.7.17-alpine3.9
             </code>
         </pre>
         <p class="gp">
             После добавления нескольких файлов или внесения изменений в контейнер вы решаете создать архив изображения, чтобы предоставить его коллеге. Вы можете достичь этого, выполнив следующую команду:
         </p>
         <pre>
             <code class="sh">
                 $ docker save python:2.7.17-alpine3.9 > /path/to/save/my-python-container.tar
             </code>
         </pre>
         <p class="gp">
             Просто убедитесь, что вы используете точное имя изображения и тег при создании tar. В нашем случае так и было python:2.7.17-alpine3.9 . Вы можете проверить, сработала ли вышеуказанная команда:
         </p>
         <pre>
             <code class="sh">
                 $ du -h my-python-container.tar
                 75M my-python-container.tar
             </code>
         </pre>
         <h3 class="gh">
             Загрузка изображения докера:
         </h3>
         <p class="gp">
             Как только на целевой машине будет файл .tar , вы можете загрузить образ в локальный реестр, используя команду docker load :
         </p>
         <pre>
             <code class="sh">
                 $ docker load < my-python-container.tar
             </code>
         </pre>
         <p class="gp">
             Теперь проверьте, есть ли у вас это изображение на целевом компьютере, с помощью docker images или docker image list . Конечный результат будет примерно таким:
         </p>

         <h1 class="gh">
             Использование команды экспорта Docker:
         </h1>
         <h3 class="gh">
             Экспорт Docker-контейнера:
         </h3>
         <p class="gp">
             Примечание. Команда docker export не будет экспортировать содержимое тома, который прикреплен к контейнеру. В этом случае вам необходимо выполнить дополнительную команду для резервного копирования, восстановления или миграции существующего тома.
         </p>
         <p class="gp">
             Посмотрев на метод docker export , сначала мы потянем альпийское изображение:
         </p>
         <pre>
             <code class="sh">
                 $ docker pull alpine
                 Using default tag: latest
                 latest: Pulling from library/alpine
                 e6b0cf9c0882: Pull complete
                 Digest: sha256:2171658620155679240babee0a7714f6509fae66898db422ad803b951257db78
                 Status: Downloaded newer image for alpine:latest
                 docker.io/library/alpine:latest
             </code>
         </pre>
         <p class="gp">
             Теперь вы можете запустить экземпляр в режиме отсоединения, чтобы контейнер не разрушался при выходе из него.
         </p>
         <pre>
             <code class="sh">
                 $ docker run -it --detach --name alpine-t alpine
             </code>
         </pre>
         <p class="gp">
             Чтобы получить идентификатор контейнера и имя, которое мы создали, мы можем использовать команду docker ps. На всякий случай, если на вашей машине контейнер был остановлен по какой-либо причине, вы все равно можете получить идентификатор и имя, используя docker ps -a :
         </p>
         <pre>
             <code class="sh">
                 $ docker ps
                 CONTAINER ID  IMAGE  COMMAND   CREATED         STATUS        PORTS    NAMES
                 35f34fabfa84  alpine "/bin/sh" 14 seconds ago  8 seconds ago           alpine-t
             </code>
         </pre>
         <p class="gp">
             Как мы видим, наш идентификатор контейнера 35f34fabfa84 (он будет другим для вас), или вы также можете использовать имя контейнера; в нашем случае это alpine-t . Теперь мы можем запустить команду docker export для экспорта изображения экземпляра:
         </p>
         <pre>
             <code class="sh">
                 $ docker export 35f34fabfa84 > alpine-t.tar
             </code>
         </pre>
         <p class="gp">
             Кроме того, вы также можете использовать OPTIONS, чтобы сделать то же самое, и ваш файл .tar будет готов для передачи.
         </p>
         <pre>
             <code class="sh">
                 $ docker export 35f34fabfa84 > alpine-t.tar
             </code>
         </pre>
         <p class="gp">
             $ docker export --output="alpine-t.tar" 35f34fabfa84
         </p>
         <h3 class="gh">
             Импорт Docker-контейнера:
         </h3>
         <p class="gp">
             Теперь вы можете импортировать файл .tar на целевой компьютер, используя импорт докера:
         </p>
         <pre>
             <code class="sh">
                 $ sudo tar -c alpine-t.tar | docker import - alpine-t
             </code>
         </pre>
         <p class="gp">
             Чтобы проверить, вы можете запустить контейнер с помощью --rm (он уничтожит контейнер, как только вы его выполните):
         </p>
         <pre>
             <code class="sh">
                 $ docker run --rm -it --name alpine-test alpine-t:[TAG]
             </code>
         </pre>'
       , 'https://cdn.shazoo.ru/c1400x625/91968_zKH6P9SLle_minions.jpg'
       , 1, 12),
       (2, 'Как создать виртуальную среду Python',
        '<p class="gp">
            Если вы похожи на меня и у вас есть десятки различных проектов Python, управление правильными пакетами, библиотеками и версиями и их поддержка могут стать настоящей проблемой. Зависимости одного проекта могут отличаться от другого, или определенные версии библиотек могут не подходить для конкретной кодовой базы. Использование виртуальных сред — отличный способ организовать ваши проекты и отделить их друг от друга. Они используются по разным причинам: от изоляции зависимостей вашего проекта до обеспечения согласованности версий пакетов и создания отдельных сред для подготовки и производства.
        </p>
        <p class="gp">
            В этом руководстве мы будем создавать виртуальную среду для простого проекта Python. Мы будем использовать модуль venv, который включен в Python 3.6 и выше. Если вы используете более раннюю версию Python, можно использовать модуль virtualenv.
        </p>
        <p class="gp">
            Давайте начнем!
        </p>
        <h3 class="gh">
            Установка venv
        </h3>
        <p class="gp">
            venv должен быть установлен по умолчанию с Python 3.6 или выше, но если он не установлен, вы можете сделать это с помощью команды:
        </p>
        <pre>
            <code class="sh">
                pip install venv
            </code>
        </pre>
        <h3 class="gh">
            Создание виртуальной среды
        </h3>
        <p class="gp">
            После установки вы можете создать виртуальную среду с помощью команды:
        </p>
        <pre>
            <code class="sh">
                python -m venv env_name
            </code>
        </pre>
        <p class="gp">
            Здесь env_name имя папки виртуальной среды — это может быть любое имя, которое вы хотите. После запуска этой команды вы увидите, что папка создана env_name.
        </p>
        <p class="gp">
            Этот каталог содержит все необходимые файлы и папки для запуска вашей виртуальной среды. Самый важный файл — это файл env.py, содержащий конфигурацию вашей виртуальной среды. Внутри папки env_name вы также найдете копию интерпретатора Python, а также исполняемые файлы pip и setuptools
        </p>
        <h3 class="gh">
            Активация виртуальной среды
        </h3>
        <p class="gp">
            Следующим шагом будет активация виртуальной среды. В зависимости от вашей ОС и используемого интерфейса командной строки имена каталогов могут немного отличаться. Чтобы активировать виртуальную среду в Linux, выполните следующую команду:
        </p>
        <pre>
            <code class="sh">
                source env_name/bin/activate
            </code>
        </pre>
        <p class="gp">
            Если вы используете Windows CMD, вам нужно будет изменить команду на:
        </p>
        <pre>
            <code class="sh">
                source env_name/Scripts/activate
            </code>
        </pre>
        <p class="gp">
            Как только виртуальная среда активна, вы сможете увидеть имя над командной строкой или проверив переменную среды VIRTUAL_ENV с помощью:
        </p>
        <pre>
            <code class="sh">
                echo $VIRTUAL_ENV
            </code>
        </pre>
        <p class="gp">
            Теперь, когда виртуальная среда активна, вы можете свободно устанавливать библиотеки и пакеты. Если вы хотите увидеть, какие версии установлены в данный момент, вы можете запустить:
        </p>
        <pre>
            <code class="sh">
                pip freeze
            </code>
        </pre>
        <p class="gp">
            Этот список распечатываемых библиотек и версий специфичен для этой виртуальной среды. Преимущество заключается в том, что вы можете использовать разные версии для проекта без необходимости менять версию пакета для каждой другой версии, что может вызвать проблемы.
        </p>
        <p class="gp">
            Вы также можете деактивировать виртуальную среду после завершения, просто запустив deactivate из любого места в каталоге.
        </p>
        <h3 class="gh">
            Выводы
        </h3>
        <p class="gp">
            Виртуальные среды — это важные инструменты для изоляции зависимостей вашего проекта друг от друга. Имея отдельные установки пакетов и двоичных файлов, каждый проект стоит сам по себе, и вы избежите поломки одного проекта обновлениями из другого.
        </p>',
        'https://habrastorage.org/getpro/habr/post_images/d49/8d3/817/d498d381796001c1acbb991bb52bc199.webp', 2, 1);

insert into reference_book (id, title, text, author_id, genre_id)
values (1, 'Реализация паттерна Builder',
        '<p class="gp">
            Реализация паттерна Builder довольно проста. Для этого нам потребуется создать вложенный статический класс, и
            реализовать конструктор с ним в основном классе. С помощью данного конструктора мы сможем заполнять поля класса.
        </p>
        <pre>
            <code class="java">
                package ru.ghost.dto;

                public class DataDto {
                    private final Long id;
                    private final String title;
                    private final String text;

                    private DataDto(final Builder builder) {
                        this.id = builder.id;
                        this.title = builder.title;
                        this.text = builder.text;
                    }

                    public static class Builder {
                        private Long id;
                        private String title;
                        private String text;

                        public Builder id(final long id) {
                            this.id = id;
                            return this;
                        }

                        public Builder title(final String title) {
                            this.title = title;
                            return this;
                        }

                        public Builder text(final String text) {
                            this.text = text;
                            return this;
                        }

                        public DataDto build() {
                            return new DataDto(this);
                        }
                    }
                }
            </code>
        </pre>
        <p class="gp">
            Теперь мы можем воспользоваться данным Builder.
        </p>
        <pre>
            <code class="java">
                new DataDto.Builder().id(1L).title("title").text("text").build();
            </code>
        </pre>',
        1, 3),
       (2, 'Реализация CompletableFuture',
        '<p class="gp">
            Простейшая реализация асинхронности с помощью цикла.
        </p>
        <pre>
            <code class="java">
                IntStream.range(1, 200).forEach(e -> {
                            CompletableFuture.runAsync(() -> {
                                long start = new Date().getTime();
                                log.info("Приехали!!!!" + e);
                                // Тут наш код
                                log.warn("===" + (new Date().getTime() - start));
                            });
                        });
            </code>
        </pre>',
        2, 3);

INSERT INTO user (id, username, password, full_name, email)
values (1, 'user', '$2a$08$uuuGT0RsSh532QIOlrS4wuTbrrUqGC2tV.WF30RVLwWmjA1Cs5yLO', 'User User', 'user@mail.com'),
       (2, 'admin', '$2a$08$uuuGT0RsSh532QIOlrS4wuTbrrUqGC2tV.WF30RVLwWmjA1Cs5yLO', 'Admin Admin', 'admin@mail.com');

INSERT INTO authority (id, user_id, authority)
values (1, 1, 'ROLE_USER'),
       (2, 2, 'ROLE_ADMIN');