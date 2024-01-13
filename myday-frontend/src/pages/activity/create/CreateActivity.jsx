import { Card, Button, message, Steps } from "antd";
import { useState } from "react";
import SelectDate from "./SelectDate";
import SelectExercise from "./SelectExercise";
import SelectMeal from "./SelectMeal";
import { reqCreateActivity } from "../../../api";

import "./CreateActivity.css";

const { Step } = Steps;

function CreateActivity() {
  const [current, setCurrent] = useState(0);
  const [createdOn, setCreatedOn] = useState("");
  const [comment, setComment] = useState("");
  const [meals, setMeals] = useState([]);
  const [exercises, setExercises] = useState([]);

  const steps = [
    {
      title: "When",
      content: (
        <SelectDate setCreatedOn={setCreatedOn} setComment={setComment} />
      ),
    },
    {
      title: "Meals",
      content: <SelectMeal setMeals={setMeals} />,
    },
    {
      title: "Exercise",
      content: <SelectExercise setExercises={setExercises} />,
    },
  ];

  const next = () => {
    setCurrent(current + 1);
  };

  // const prev = () => {
  //   setCurrent(current - 1);
  // };

  const onSubmit = () => {
    console.log(createdOn);
    console.log(comment);
    console.log(meals);
    console.log(exercises);
    if (!createdOn || createdOn.length === 0) {
      message.warn("Date information is missing");
      setCurrent(0);
      return;
    }
    if (!meals || meals.length === 0) {
      message.warn("Meal information is missing");
      setCurrent(0);
      return;
    }
    if (!exercises || exercises.length === 0) {
      message.warn("Exercise information is missing");
      setCurrent(0);
      return;
    }

    const mealRecords = meals.map((meal) => ({
      mealId: meal.id,
      servings: meal.servings,
    }));
    const exerciseRecords = exercises.map((exercise) => ({
      exerciseId: exercise.id,
      duration: exercise.duration,
    }));
    const activity = { mealRecords, exerciseRecords, createdOn, comment };
    reqCreateActivity(activity);
    message.success("Processing complete!");
  };

  return (
    <Card>
      <Steps current={current}>
        {steps.map((item) => (
          <Step key={item.title} title={item.title} />
        ))}
      </Steps>
      <div className="steps-content">{steps[current].content}</div>
      <div className="steps-action">
        {current < steps.length - 1 && (
          <Button type="primary" onClick={() => next()}>
            Next
          </Button>
        )}
        {current === steps.length - 1 && (
          <Button type="primary" onClick={onSubmit}>
            Submit
          </Button>
        )}
        <Button
          style={{
            margin: "0 8px",
          }}
          onClick={() => setCurrent(0)}
        >
          Restart
        </Button>
        {/* {current > 0 && (
          <Button
            style={{
              margin: "0 8px",
            }}
            onClick={() => prev()}
          >
            Previous
          </Button>
        )} */}
      </div>
    </Card>
  );
}

export default CreateActivity;
