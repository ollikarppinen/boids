package boids

import scala.swing.{ SimpleSwingApplication, MainFrame, BorderPanel, Button, Slider }
import scala.swing.event.{ ButtonClicked, ValueChanged }

object Gui extends SimpleSwingApplication {
  val top = new MainFrame {
    title = "Boid simulation"
    resizable = false

    contents = new BorderPanel {
      layout(SimulationPanel) = BorderPanel.Position.Center
      layout(SettingsPanel) = BorderPanel.Position.East
    }
    peer.setLocationRelativeTo(null)
  }
  listenTo(SettingsPanel.massSlider,
    SettingsPanel.distanceSlider,
    SettingsPanel.maxForceSlider,
    SettingsPanel.maxSpeedSlider,
    SettingsPanel.flockSizeSlider,
    SettingsPanel.separationSlider,
    SettingsPanel.cohesionSlider,
    SettingsPanel.alignmentSlider,
    SettingsPanel.pauseButton,
    SettingsPanel.restartButton,
    SettingsPanel.resetButton,
    SettingsPanel.randomButton)
  reactions += {
    case ButtonClicked(source) => {
      val button = source.asInstanceOf[Button]
      button match {
        case SettingsPanel.pauseButton   => SimulationPanel.pause()
        case SettingsPanel.restartButton => SimulationPanel.restart()
        case SettingsPanel.resetButton => {
          SettingsPanel.massSlider.value = 1
          SettingsPanel.distanceSlider.value = 10
          SettingsPanel.maxForceSlider.value = 10
          SettingsPanel.maxSpeedSlider.value = 10
          SettingsPanel.flockSizeSlider.value = 200
          SettingsPanel.separationSlider.value = 10
          SettingsPanel.cohesionSlider.value = 10
          SettingsPanel.alignmentSlider.value = 10
        }
        case SettingsPanel.randomButton => {
          val r = scala.util.Random
          def randomSliderValue(s: Slider) = s.min + r.nextInt(s.max - s.min)
          SettingsPanel.massSlider.value = randomSliderValue(SettingsPanel.massSlider)
          SettingsPanel.distanceSlider.value = randomSliderValue(SettingsPanel.distanceSlider)
          SettingsPanel.maxForceSlider.value = randomSliderValue(SettingsPanel.maxForceSlider)
          SettingsPanel.maxSpeedSlider.value = randomSliderValue(SettingsPanel.maxSpeedSlider)
          SettingsPanel.flockSizeSlider.value = randomSliderValue(SettingsPanel.flockSizeSlider)
          SettingsPanel.separationSlider.value = randomSliderValue(SettingsPanel.separationSlider)
          SettingsPanel.cohesionSlider.value = randomSliderValue(SettingsPanel.cohesionSlider)
          SettingsPanel.alignmentSlider.value = randomSliderValue(SettingsPanel.alignmentSlider)
        }
      }
    }
    case ValueChanged(source) => {
      val slider = source.asInstanceOf[Slider]
      slider match {
        case SettingsPanel.massSlider      => Boid.mass = slider.value
        case SettingsPanel.distanceSlider  => Boid.distance = slider.value * slider.value
        case SettingsPanel.maxForceSlider  => Boid.maxForce = slider.value
        case SettingsPanel.maxSpeedSlider  => Boid.maxSpeed = 0.1f * slider.value
        case SettingsPanel.flockSizeSlider => Simulation.size = slider.value
        case SettingsPanel.separationSlider => Boid.separationWeight = 0.1f * slider.value
        case SettingsPanel.cohesionSlider => Boid.cohesionWeight = 0.1f * slider.value
        case SettingsPanel.alignmentSlider => Boid.alignmentWeight = 0.1f * slider.value
      }
    }
  }
}