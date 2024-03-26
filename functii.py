# 1. Sa se creeze o functie care spune ce film din lista de filme este cel mai vechi.

def get_oldest_movie(list_of_movies):
    oldest_movie = list_of_movies[0]
    for movie in list_of_movies:
        if oldest_movie["year"] > movie["year"]:
            oldest_movie = movie
    return oldest_movie

# print(get_oldest_movie(list_of_movies))

# print([movie for movie in list_of_movies if movie.get("year") == min([movie.get("year") for movie in list_of_movies])][0])

# 2. Sa se creeze o functie care spune ce film din lista de filme este cel mai nou.

def get_newest_movie(list_of_movies:list):
    newest_movie = list_of_movies[0]
    for movie in list_of_movies:
        if newest_movie["year"] < movie["year"]:
            newest_movie = movie
    return newest_movie


# print(get_newest_movie(list_of_movies))

# print([movie for movie in list_of_movies if movie.get("year") == max([movie.get("year") for movie in list_of_movies])][0])

# 3. Sa se creeze o functie care spune ce film din lista de filme are cei mai multi actori.

def get_max_actors(list_of_movies):
    max_actors = list_of_movies[0]
    for actors_in_movies in list_of_movies:
        if len(max_actors["actors"]) < len(actors_in_movies["actors"]):
           max_actors = actors_in_movies
    return max_actors

# print(get_max_actors(list_of_movies))

# 4. Sa se creeze o functie care spune ce film din lista de filme are cele mai multe premii.

def get_most_awards(list_of_movies):
    most_awards = list_of_movies[0]
    for movie in list_of_movies:
        if len(most_awards["awards"]) < len(movie["awards"]):
            most_awards = movie
    return most_awards

# print(get_most_awards(list_of_movies))

# 5.Sa se creeze o functie care spune ce film din lista de filme are cele mai putine premii.

def get_less_awards(list_of_movies):
    less_awards = list_of_movies[0]
    for movie in list_of_movies:
        if len(less_awards["awards"]) > len(movie["awards"]) and 2010 > movie["year"] > 2000:
            less_awards = movie
    return less_awards

# print(get_less_awards(list_of_movies))
# 7.Sa se creeze o functie care spune ce film din lista de filme este cel mai lung.

def get_longest_movie(list_of_movies):
    max_runtime = list_of_movies[0]
    for movie in list_of_movies:
        if max_runtime.get("runtime") < movie.get("runtime"):
            max_runtime = movie
    return max_runtime

# print(get_longest_movie(list_of_movies))

# 8.Sa se creeze o functie care spune ce film din lista de filme este cel mai scurt.

def get_shortest_movie(list_of_movies):
    min_runtime = list_of_movies[0]
    for movie in list_of_movies:
        if min_runtime.get("runtime") > movie.get("runtime"):
            min_runtime = movie
    return min_runtime
# 9.Sa se creeze o functie care intoarce o lista care contine toate filemele care au obtinut:Academy Award - Best Makeup.

def get_makeup_award(list_of_movies)->list:
    best_makeup_award = []
    for movie in list_of_movies:
        if "Academy Award - Best Makeup" in movie.get("awards"):
            best_makeup_award.append(movie)
    return best_makeup_award

# 10. Sa se creeze o functie care intoarce un dictionar care are ca si key tara si ca si valoare o lista cu filmele care s-au jucat in aceea tara.

def country_movie(list_of_movies):
    ctr_movie = dict()
    for movie in list_of_movies:
        if movie.get("country") in ctr_movie:
            ctr_movie[movie.get("country")].append(movie.get("title"))
        else:
            ctr_movie[movie.get("country")] = [movie.get("title")]
    return ctr_movie
