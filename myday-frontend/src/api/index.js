import ajax from "./ajax";
import { authHeader } from "./header";

const BASE_URL = `${process.env.REACT_APP_BASE_URL}`;

export const reqWeather = (city) =>
  ajax(
    "https://api.openweathermap.org/data/2.5/weather",
    {
      q: `${city}`,
      appid: `${process.env.REACT_APP_WEATHER_API_KEY}`,
      units: "metric",
    },
    "get"
  );

export const reqLogin = (username, password) =>
  ajax(`${BASE_URL}/auth/login`, { username, password }, "post");

export const reqRegister = (username, password, email) =>
  ajax(`${BASE_URL}/auth/register`, { username, password, email }, "post");

export const reqExerciseCategories = () =>
  ajax(`${BASE_URL}/exercise/category/all`, {}, "get", authHeader());

export const reqVisibleExerciseCategories = () =>
  ajax(`${BASE_URL}/exercise/category/all-visible`, {}, "get", authHeader());

export const reqAddExerciseCategory = (name, desc = "") =>
  ajax(
    `${BASE_URL}/exercise/category/create`,
    { name, desc },
    "post",
    authHeader()
  );

export const reqUpdateExerciseCategory = (id, name, desc, avatar) =>
  ajax(
    `${BASE_URL}/exercise/category/update`,
    { id, name, desc, avatar },
    "put",
    authHeader()
  );

export const reqDeleteExerciseCategory = (id) =>
  ajax(`${BASE_URL}/exercise/category/${id}`, {}, "delete", authHeader());

export const reqExercises = () =>
  ajax(`${BASE_URL}/exercise/all`, {}, "get", authHeader());

export const reqVisibleExercises = () =>
  ajax(`${BASE_URL}/exercise/all-visible`, {}, "get", authHeader());

// Search by exercise or category name (name or category)
export const reqVisibleExercisesBy = (searchType, keywords) =>
  ajax(
    `${BASE_URL}/exercise/all-visible`,
    { [searchType]: keywords },
    "get",
    authHeader()
  );

export const reqVisibleExercisesByCategory = (category) =>
  ajax(`${BASE_URL}/exercise/all-visible`, { category }, "get", authHeader());

export const reqAddExercise = (name, met, categoryId, desc = "") =>
  ajax(
    `${BASE_URL}/exercise/create`,
    { name, met, categoryId, desc },
    "post",
    authHeader()
  );

export const reqUpdateExercise = (id, name, met, categoryId, desc = "") =>
  ajax(
    `${BASE_URL}/exercise/update`,
    { id, name, met, categoryId, desc },
    "put",
    authHeader()
  );

export const reqAddOrUpdateExercise = (exercise) => {
  const { id, name, met, categoryId, desc } = exercise;
  if (id)
    return ajax(
      `${BASE_URL}/exercise/update`,
      { id, name, met, categoryId, desc },
      "put",
      authHeader()
    );
  return ajax(
    `${BASE_URL}/exercise/create`,
    { name, met, categoryId, desc },
    "post",
    authHeader()
  );
};

export const reqDeleteExercise = (id) =>
  ajax(`${BASE_URL}/exercise/${id}`, {}, "delete", authHeader());



export const reqMealCategories = () =>
  ajax(`${BASE_URL}/meal/category/all`, {}, "get", authHeader());

export const reqVisibleMealCategories = () =>
  ajax(`${BASE_URL}/meal/category/all-visible`, {}, "get", authHeader());

export const reqAddMealCategory = (name, desc = "") =>
  ajax(
    `${BASE_URL}/meal/category/create`,
    { name, desc },
    "post",
    authHeader()
  );

export const reqUpdateMealCategory = (id, name, desc, avatar) =>
  ajax(
    `${BASE_URL}/meal/category/update`,
    { id, name, desc, avatar },
    "put",
    authHeader()
  );

export const reqDeleteMealCategory = (id) =>
  ajax(`${BASE_URL}/meal/category/${id}`, {}, "delete", authHeader());




export const reqMeals = () =>
  ajax(`${BASE_URL}/meal/all`, {}, "get", authHeader());

export const reqVisibleMeals = () =>
  ajax(`${BASE_URL}/meal/all-visible`, {}, "get", authHeader());

// Search by meal or category name (name or category)
export const reqVisibleMealsBy = (searchType, keywords) =>
  ajax(
    `${BASE_URL}/meal/all-visible`,
    { [searchType]: keywords },
    "get",
    authHeader()
  );

export const reqVisibleMealsByCategory = (category) =>
  ajax(`${BASE_URL}/meal/all-visible`, { category }, "get", authHeader());

export const reqAddMeal = (name, calPerServing, categoryId, desc = "") =>
  ajax(
    `${BASE_URL}/exercise/create`,
    { name, calPerServing, categoryId, desc },
    "post",
    authHeader()
  );

export const reqUpdateMeal = (id, name, calPerServing, categoryId, desc = "") =>
  ajax(
    `${BASE_URL}/exercise/update`,
    { id, name, calPerServing, categoryId, desc },
    "put",
    authHeader()
  );

export const reqAddOrUpdateMeal = (meal) => {
  const { id, name, calPerServing, categoryId, desc } = meal;
  if (id)
    return ajax(
      `${BASE_URL}/meal/update`,
      { id, name, calPerServing, categoryId, desc },
      "put",
      authHeader()
    );
  return ajax(
    `${BASE_URL}/meal/create`,
    { name, calPerServing, categoryId, desc },
    "post",
    authHeader()
  );
};

export const reqDeleteMeal = (id) =>
  ajax(`${BASE_URL}/meal/${id}`, {}, "delete", authHeader());


export const reqCreateActivity = (activity) =>
  ajax(`${BASE_URL}/activity/create`, {...activity}, "post", authHeader());

export const reqUserActivities = () =>
  ajax(`${BASE_URL}/activity/user`, {}, "get", authHeader());

export const reqDeleteActivity = (id) =>
  ajax(`${BASE_URL}/activity/${id}`, {}, "delete", authHeader());