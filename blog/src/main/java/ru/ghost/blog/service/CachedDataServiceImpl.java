package ru.ghost.blog.service;

import org.springframework.stereotype.Service;
import ru.ghost.blog.model.Author;
import ru.ghost.blog.model.Genre;
import ru.ghost.blog.model.Post;
import ru.ghost.blog.model.ReferenceBook;

@Service
public class CachedDataServiceImpl implements CachedDataService {
    @Override
    public Author getAuthor() {
        return Author.builder()
                .id(1L)
                .firstName("Иванов")
                .lastName("Иван")
                .build();
    }

    @Override
    public Genre getGenre() {
        return Genre.builder()
                .id(3L)
                .name("java")
                .build();
    }

    @Override
    public Post getPost() {
        return Post.builder()
                .id(1L)
                .author(this.getAuthor())
                .title("Как перенести / переместить образ Docker в другую систему?")
                .image("https://cdn.shazoo.ru/c1400x625/91968_zKH6P9SLle_minions.jpg")
                .genre(Genre.builder()
                        .id(12L)
                        .name("docker")
                        .build())
                .text("<p class=\"gp\">\n" +
                        "             В идеальном случае передача изображений Docker осуществляется через реестр Docker или через полностью управляемого поставщика, такого как AWS ECR или Google GCR. Вы можете легко загрузить изображение с помощью команды docker push , а другие могут получить изображение с помощью команды docker pull .\n" +
                        "         </p>\n" +
                        "         <p class=\"gp\">\n" +
                        "             Хотя, если вам нужно переместить изображение с одного хоста на другой, чтобы протестировать его перед отправкой в производственную среду, или вы хотите поделиться этим изображением с кем-то в офисе, это можно сделать, экспортировав изображение как .tar файл.\n" +
                        "         </p>\n" +
                        "         <p class=\"gp\">\n" +
                        "             Docker поддерживает два разных типа методов для сохранения изображений контейнера в один архив.\n" +
                        "         </p>\n" +
                        "         <ui>\n" +
                        "             <li>Docker save - Save используется для сохранения изображения (не контейнера)</li>\n" +
                        "             <li>Docker export - Export используется для сохранения контейнера (не изображения)</li>\n" +
                        "         </ui>\n" +
                        "         <h1 class=\"gh\">\n" +
                        "             Использование Docker Save Command:\n" +
                        "         </h1>\n" +
                        "         <h3 class=\"gh\">\n" +
                        "             Сохранение изображения Docker:\n" +
                        "         </h3>\n" +
                        "         <p class=\"gp\">\n" +
                        "             Во-первых, мы будем придерживаться плана, который сохраняет только изображение. Теперь пройдемся по команде docker save . Предположим, вам нужен образ Python с Alpine, который можно извлечь из Docker Hub:\n" +
                        "         </p>\n" +
                        "         <pre>\n" +
                        "             <code class=\"sh\">\n" +
                        "                 $ docker pull python:2.7.17-alpine3.9\n" +
                        "                 2.7.17-alpine3.9: Pulling from library/python\n" +
                        "                 e7c96db7181b: Already exists\n" +
                        "                 1819f4b92bc2: Already exists\n" +
                        "                 8061b3761cb3: Pull complete\n" +
                        "                 73aebae115de: Pull complete\n" +
                        "                 Digest: sha256:5f6059d78f530c3c59c4842e104ddcfc772a27fb8fac0d900f4d77bcb4621d9b\n" +
                        "                 Status: Downloaded newer image for python:2.7.17-alpine3.9\n" +
                        "                 docker.io/library/python:2.7.17-alpine3.9\n" +
                        "             </code>\n" +
                        "         </pre>\n" +
                        "         <p class=\"gp\">\n" +
                        "             После добавления нескольких файлов или внесения изменений в контейнер вы решаете создать архив изображения, чтобы предоставить его коллеге. Вы можете достичь этого, выполнив следующую команду:\n" +
                        "         </p>\n" +
                        "         <pre>\n" +
                        "             <code class=\"sh\">\n" +
                        "                 $ docker save python:2.7.17-alpine3.9 > /path/to/save/my-python-container.tar\n" +
                        "             </code>\n" +
                        "         </pre>\n" +
                        "         <p class=\"gp\">\n" +
                        "             Просто убедитесь, что вы используете точное имя изображения и тег при создании tar. В нашем случае так и было python:2.7.17-alpine3.9 . Вы можете проверить, сработала ли вышеуказанная команда:\n" +
                        "         </p>\n" +
                        "         <pre>\n" +
                        "             <code class=\"sh\">\n" +
                        "                 $ du -h my-python-container.tar\n" +
                        "                 75M my-python-container.tar\n" +
                        "             </code>\n" +
                        "         </pre>\n" +
                        "         <h3 class=\"gh\">\n" +
                        "             Загрузка изображения докера:\n" +
                        "         </h3>\n" +
                        "         <p class=\"gp\">\n" +
                        "             Как только на целевой машине будет файл .tar , вы можете загрузить образ в локальный реестр, используя команду docker load :\n" +
                        "         </p>\n" +
                        "         <pre>\n" +
                        "             <code class=\"sh\">\n" +
                        "                 $ docker load < my-python-container.tar\n" +
                        "             </code>\n" +
                        "         </pre>\n" +
                        "         <p class=\"gp\">\n" +
                        "             Теперь проверьте, есть ли у вас это изображение на целевом компьютере, с помощью docker images или docker image list . Конечный результат будет примерно таким:\n" +
                        "         </p>\n" +
                        "\n" +
                        "         <h1 class=\"gh\">\n" +
                        "             Использование команды экспорта Docker:\n" +
                        "         </h1>\n" +
                        "         <h3 class=\"gh\">\n" +
                        "             Экспорт Docker-контейнера:\n" +
                        "         </h3>\n" +
                        "         <p class=\"gp\">\n" +
                        "             Примечание. Команда docker export не будет экспортировать содержимое тома, который прикреплен к контейнеру. В этом случае вам необходимо выполнить дополнительную команду для резервного копирования, восстановления или миграции существующего тома.\n" +
                        "         </p>\n" +
                        "         <p class=\"gp\">\n" +
                        "             Посмотрев на метод docker export , сначала мы потянем альпийское изображение:\n" +
                        "         </p>\n" +
                        "         <pre>\n" +
                        "             <code class=\"sh\">\n" +
                        "                 $ docker pull alpine\n" +
                        "                 Using default tag: latest\n" +
                        "                 latest: Pulling from library/alpine\n" +
                        "                 e6b0cf9c0882: Pull complete\n" +
                        "                 Digest: sha256:2171658620155679240babee0a7714f6509fae66898db422ad803b951257db78\n" +
                        "                 Status: Downloaded newer image for alpine:latest\n" +
                        "                 docker.io/library/alpine:latest\n" +
                        "             </code>\n" +
                        "         </pre>\n" +
                        "         <p class=\"gp\">\n" +
                        "             Теперь вы можете запустить экземпляр в режиме отсоединения, чтобы контейнер не разрушался при выходе из него.\n" +
                        "         </p>\n" +
                        "         <pre>\n" +
                        "             <code class=\"sh\">\n" +
                        "                 $ docker run -it --detach --name alpine-t alpine\n" +
                        "             </code>\n" +
                        "         </pre>\n" +
                        "         <p class=\"gp\">\n" +
                        "             Чтобы получить идентификатор контейнера и имя, которое мы создали, мы можем использовать команду docker ps. На всякий случай, если на вашей машине контейнер был остановлен по какой-либо причине, вы все равно можете получить идентификатор и имя, используя docker ps -a :\n" +
                        "         </p>\n" +
                        "         <pre>\n" +
                        "             <code class=\"sh\">\n" +
                        "                 $ docker ps\n" +
                        "                 CONTAINER ID  IMAGE  COMMAND   CREATED         STATUS        PORTS    NAMES\n" +
                        "                 35f34fabfa84  alpine \"/bin/sh\" 14 seconds ago  8 seconds ago           alpine-t\n" +
                        "             </code>\n" +
                        "         </pre>\n" +
                        "         <p class=\"gp\">\n" +
                        "             Как мы видим, наш идентификатор контейнера 35f34fabfa84 (он будет другим для вас), или вы также можете использовать имя контейнера; в нашем случае это alpine-t . Теперь мы можем запустить команду docker export для экспорта изображения экземпляра:\n" +
                        "         </p>\n" +
                        "         <pre>\n" +
                        "             <code class=\"sh\">\n" +
                        "                 $ docker export 35f34fabfa84 > alpine-t.tar\n" +
                        "             </code>\n" +
                        "         </pre>\n" +
                        "         <p class=\"gp\">\n" +
                        "             Кроме того, вы также можете использовать OPTIONS, чтобы сделать то же самое, и ваш файл .tar будет готов для передачи.\n" +
                        "         </p>\n" +
                        "         <pre>\n" +
                        "             <code class=\"sh\">\n" +
                        "                 $ docker export 35f34fabfa84 > alpine-t.tar\n" +
                        "             </code>\n" +
                        "         </pre>\n" +
                        "         <p class=\"gp\">\n" +
                        "             $ docker export --output=\"alpine-t.tar\" 35f34fabfa84\n" +
                        "         </p>\n" +
                        "         <h3 class=\"gh\">\n" +
                        "             Импорт Docker-контейнера:\n" +
                        "         </h3>\n" +
                        "         <p class=\"gp\">\n" +
                        "             Теперь вы можете импортировать файл .tar на целевой компьютер, используя импорт докера:\n" +
                        "         </p>\n" +
                        "         <pre>\n" +
                        "             <code class=\"sh\">\n" +
                        "                 $ sudo tar -c alpine-t.tar | docker import - alpine-t\n" +
                        "             </code>\n" +
                        "         </pre>\n" +
                        "         <p class=\"gp\">\n" +
                        "             Чтобы проверить, вы можете запустить контейнер с помощью --rm (он уничтожит контейнер, как только вы его выполните):\n" +
                        "         </p>\n" +
                        "         <pre>\n" +
                        "             <code class=\"sh\">\n" +
                        "                 $ docker run --rm -it --name alpine-test alpine-t:[TAG]\n" +
                        "             </code>\n" +
                        "         </pre>")
                .build();
    }

    @Override
    public ReferenceBook getReferenceBook() {
        return ReferenceBook.builder()
                .id(1L)
                .author(this.getAuthor())
                .genre(this.getGenre())
                .title("Реализация паттерна Builder")
                .text("<p class=\"gp\">\n" +
                        "            Реализация паттерна Builder довольно проста. Для этого нам потребуется создать вложенный статический класс, и\n" +
                        "            реализовать конструктор с ним в основном классе. С помощью данного конструктора мы сможем заполнять поля класса.\n" +
                        "        </p>\n" +
                        "        <pre>\n" +
                        "            <code class=\"java\">\n" +
                        "                package ru.ghost.dto;\n" +
                        "\n" +
                        "                public class DataDto {\n" +
                        "                    private final Long id;\n" +
                        "                    private final String title;\n" +
                        "                    private final String text;\n" +
                        "\n" +
                        "                    private DataDto(final Builder builder) {\n" +
                        "                        this.id = builder.id;\n" +
                        "                        this.title = builder.title;\n" +
                        "                        this.text = builder.text;\n" +
                        "                    }\n" +
                        "\n" +
                        "                    public static class Builder {\n" +
                        "                        private Long id;\n" +
                        "                        private String title;\n" +
                        "                        private String text;\n" +
                        "\n" +
                        "                        public Builder id(final long id) {\n" +
                        "                            this.id = id;\n" +
                        "                            return this;\n" +
                        "                        }\n" +
                        "\n" +
                        "                        public Builder title(final String title) {\n" +
                        "                            this.title = title;\n" +
                        "                            return this;\n" +
                        "                        }\n" +
                        "\n" +
                        "                        public Builder text(final String text) {\n" +
                        "                            this.text = text;\n" +
                        "                            return this;\n" +
                        "                        }\n" +
                        "\n" +
                        "                        public DataDto build() {\n" +
                        "                            return new DataDto(this);\n" +
                        "                        }\n" +
                        "                    }\n" +
                        "                }\n" +
                        "            </code>\n" +
                        "        </pre>\n" +
                        "        <p class=\"gp\">\n" +
                        "            Теперь мы можем воспользоваться данным Builder.\n" +
                        "        </p>\n" +
                        "        <pre>\n" +
                        "            <code class=\"java\">\n" +
                        "                new DataDto.Builder().id(1L).title(\"title\").text(\"text\").build();\n" +
                        "            </code>\n" +
                        "        </pre>',\n" +
                        "        1, 3),\n" +
                        "       (2, 'Реализация CompletableFuture',\n" +
                        "        '<p class=\"gp\">\n" +
                        "            Простейшая реализация асинхронности с помощью цикла.\n" +
                        "        </p>\n" +
                        "        <pre>\n" +
                        "            <code class=\"java\">\n" +
                        "                IntStream.range(1, 200).forEach(e -> {\n" +
                        "                            CompletableFuture.runAsync(() -> {\n" +
                        "                                long start = new Date().getTime();\n" +
                        "                                log.info(\"Приехали!!!!\" + e);\n" +
                        "                                // Тут наш код\n" +
                        "                                log.warn(\"===\" + (new Date().getTime() - start));\n" +
                        "                            });\n" +
                        "                        });\n" +
                        "            </code>\n" +
                        "        </pre>")
                .build();
    }
}
