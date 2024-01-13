import { Input, Card, Tooltip, Button, List, Cascader } from "antd";
import { SearchOutlined } from "@ant-design/icons";
import { useState, useEffect } from "react";
import { reqVisibleExercises, reqVisibleExercisesBy } from "../../../api";
import Calculate from "./Calculate";

const { Grid } = Card;

const gridStyle = {
  width: "50%",
  textAlign: "center",
};

function Calories() {
  const [exercise, setExercise] = useState([]);
  const [exercises, setExercises] = useState([]);
  const [searchWords, setSearchWords] = useState("");
  const [options, setOptions] = useState([]);

  const fetchExerciesByWord = async () => {
    const result = await reqVisibleExercisesBy("name", searchWords);
    setExercises(result);
  };

  const fetchOptions = async () => {
    const exercises = await reqVisibleExercises();
    const nameSet = [...new Set(exercises.map((e) => e.name))];
    const options = nameSet.map((name) => ({
      value: name,
      label: name,
      isLeaf: false,
    }));
    console.log(options);
    setOptions(options);
  };

  useEffect(() => {
    fetchOptions();
  }, []);

  const loadData = async (selectedOptions) => {
    const targetOption = selectedOptions[0];
    targetOption.loading = true; // load options lazily

    const result = await reqVisibleExercisesBy("name", targetOption.value);
    targetOption.children = result.map((e) => ({
      value: e,
      label: e.desc,
      isLeaf: true,
    }));
    setOptions([...options]);
  };

  const onChangeOptions = (_, selectedOptions) => {
    const exercise =
      selectedOptions.length > 1 ? selectedOptions[1].value : null;
    console.log(exercise);
    if (exercise) {
      setExercise(exercise);
    }
  };

  return (
    <Card title="Calories Burned From Exercise" bordered="false">
      <Grid hoverable={false} style={gridStyle}>
        <p> Search exercise by name</p>
        <Input
          placeholder="Keywords"
          style={{ width: 150, margin: "0 5px" }}
          onChange={(e) => setSearchWords(e.target.value)}
        />
        <Tooltip title="search">
          <Button
            onClick={fetchExerciesByWord}
            shape="circle"
            icon={<SearchOutlined />}
          />
        </Tooltip>
      </Grid>

      <Grid hoverable={false} style={gridStyle}>
        <p>Or select exercise from menu</p>
        <Cascader
          options={options}
          loadData={loadData}
          onChange={onChangeOptions}
        />
      </Grid>

      <Grid hoverable={false} style={gridStyle}>
        <List
          size="small"
          dataSource={exercises}
          renderItem={(item) => (
            <List.Item>
              <Button type="link" onClick={() => setExercise(item)}>
                {item.name}: {item.desc}
              </Button>
            </List.Item>
          )}
        />
      </Grid>
      <Grid hoverable={false} style={gridStyle}>
        <Calculate exercise={exercise} />
      </Grid>
    </Card>
  );
}

export default Calories;
