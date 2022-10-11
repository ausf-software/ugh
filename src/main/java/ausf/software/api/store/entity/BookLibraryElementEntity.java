/*
 * Copyright © 2022 Shcherbina Daniil and BouH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ausf.software.api.store.entity;

import ausf.software.api.store.BookCategory;
import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

/**
 * Описывает элемент из таблицы, содержащей информацию о рекомендуемых
 * одногрупниками книгах.
 *
 * @see BookCategory
 * @author Shcherbina Daniil
 * @since 1.0
 * @version 1.0
 */
@Entity
@Table(name = "book_library")
public class BookLibraryElementEntity {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;

    @Column (name = "category")
    @NotNull
    private int category;

    @Column (name = "author")
    @NotNull
    private String author;

    @Column (name = "title")
    @NotNull
    private String title;

    @Column (name = "description")
    private String description;

    @Column (name = "recommended")
    private String recommendedBy;

    public BookLibraryElementEntity(@NotNull int category, @NotNull String author, @NotNull String title, String description, String recommendedBy) {
        this.category = category;
        this.author = author;
        this.title = title;
        this.description = description;
        this.recommendedBy = recommendedBy;
    }

    public BookLibraryElementEntity() {
    }

    public int getId() {
        return id;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(@NotNull int category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(@NotNull String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(@NotNull String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRecommendedBy() {
        return recommendedBy;
    }

    public void setRecommendedBy(String recommendedBy) {
        this.recommendedBy = recommendedBy;
    }

}
